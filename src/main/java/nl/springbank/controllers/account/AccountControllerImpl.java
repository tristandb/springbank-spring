package nl.springbank.controllers.account;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.helper.CardHelper;
import nl.springbank.helper.DateHelper;
import nl.springbank.helper.IbanHelper;
import nl.springbank.objects.OpenedAccount;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.CardService;
import nl.springbank.services.UserService;
import nl.springbank.services.IBANService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 *
 * @author Tristan de Boer.
 */
@Service
@AutoJsonRpcServiceImpl
public class AccountControllerImpl implements AccountController {

    private final ReentrantLock iBANlock;

    private final ReentrantLock cardLock;

    @Autowired
    private UserService userService;

    @Autowired
    private IBANService IBANService;

    @Autowired
    private BankAccountService bankAccountService;
    
    @Autowired
    private CardService cardService;

    public AccountControllerImpl() {
        this.cardLock = new ReentrantLock();
        this.iBANlock = new ReentrantLock();
    }

    @Override
    public OpenedAccount openAccount(@JsonRpcParam("name") String name, @JsonRpcParam("surname") String surname, @JsonRpcParam("initials") String initials, @JsonRpcParam("dob") String dob, @JsonRpcParam("ssn") String ssn, @JsonRpcParam("address") String address, @JsonRpcParam("telephoneNumber") String telephoneNumber, @JsonRpcParam("email") String email, @JsonRpcParam("username") String username, @JsonRpcParam("password") String password) throws InvalidParamValueError {

        // Create a user account
        UserBean userBean = new UserBean();
        userBean.setEmail(email);
        userBean.setDateOfBirth(new java.sql.Date(DateHelper.getDateFromString(dob).getTime()));
        userBean.setName(name);
        userBean.setSurname(surname);
        userBean.setBsn(ssn);
        userBean.setInitials(initials);
        userBean.setPassword(password);
        userBean.setStreetAddress(address);
        userBean.setTelephoneNumber(telephoneNumber);
        userBean.setUsername(username);
        long userId;
        try {
            userId = userService.saveUser(userBean).getId();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new InvalidParamValueError("Invalid parameters given");
        }

        return this.openBankAccountandCard(userId);
    }

    /**
     * Open an additional account for an existing customer. Also creates a PIN card for this account.
     *
     * @param authToken The authentication token, obtained with getAuthToken.
     * @return A dictionary containing details about the new account.
     */
    @Override
    public OpenedAccount openAdditionalAccount(@JsonRpcParam("authToken") String authToken) throws NotAuthorizedError {
        long userId = AuthenticationHelper.getUserId(authToken);
        return this.openBankAccountandCard(userId);
    }

    /**
     * Close a bank account. Also invalidates the corresponding pin card. If this is the customers
     * last bank account, it also closes the customer account.
     * @param authToken The authentication token, obtained with getAuthToken
     * @param iBAN (String) The number of the bank account
     * @return An empty dictionary if successful
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message
     * @throws NotAuthorizedError The authenticated user is not authorized to perform this action.
     */
    @Override
    public void closeAccount(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("iBAN") String iBAN) throws InvalidParamValueError, NotAuthorizedError {
        long userId = AuthenticationHelper.getUserId(authToken);

        IbanBean ibanBean = IBANService.getIbanBean(iBAN);

        // Check if iBAN exists
        if (ibanBean == null){
            throw new InvalidParamValueError("iBAN does not exist");
        }

        List<BankAccountBean> userBankAccounts = bankAccountService.getUserBankAccounts(userId);
        boolean userMayDeleteAccount = false;
        for (BankAccountBean userBankAccount: userBankAccounts) {
            if (userBankAccount.getBankAccountId() == ibanBean.getBankAccountId()){
                userMayDeleteAccount = true;
                break;
            }
        }

        if (userMayDeleteAccount) {
            // Delete bank account
            bankAccountService.deleteBankAccount(ibanBean.getBankAccountId());

            // Delete user if last bank account
            if (userBankAccounts.size() == 1) {
                userService.deleteUser(userId);
            }
        } else {
            throw new NotAuthorizedError();
        }
    }

    /**
     * Opens a bank account given a userId, creates an iBAN and adds a card.
     * @param userId
     * @return
     */
    private OpenedAccount openBankAccountandCard(long userId){
        // Create BankAccountController
        BankAccountBean bankAccountBean = new BankAccountBean();
        bankAccountBean.setUserId(userId);
        bankAccountBean = bankAccountService.saveBankAccount(bankAccountBean);

        IbanBean ibanBean = new IbanBean();
        try {
            // Lock to avoid duplicate iBAN
            iBANlock.lock();
            // Generate iBAN
            String iBAN = IbanHelper.generateIban(IBANService.getAllIBAN());

            // Connect BankAccountController to iBAN
            ibanBean.setIban(iBAN);
            ibanBean.setBankAccountId(bankAccountBean.getBankAccountId());
            IBANService.saveIbanBean(ibanBean);
        } finally {
            // Unlock iBAN
            iBANlock.unlock();
        }

        // Create Card
        CardBean cardBean = new CardBean();
        try {
            cardLock.lock();
            List<Integer> cardList = cardService.getCardNumbers(bankAccountBean.getBankAccountId());
            Integer cardId = CardHelper.getRandomCardNumber(cardList);
            cardBean.setBankAccountId(bankAccountBean.getBankAccountId());
            cardBean.setCardNumber(cardId);
            cardBean.setExpirationDate(CardHelper.getExpirationDate());
            cardBean.setPin(CardHelper.getRandomPin());
            cardService.saveCardBean(cardBean);
        } finally {
            cardLock.unlock();
        }

        return new OpenedAccount(ibanBean.getIban(), CardHelper.convertToString(cardBean.getCardNumber()), CardHelper.convertToString(cardBean.getPin()));
    }
}
