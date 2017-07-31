package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.helper.CardHelper;
import nl.springbank.objects.OpenedCard;
import nl.springbank.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
@Service
@AutoJsonRpcServiceImpl
public class AccessControllerImpl implements AccessController {

    private final ReentrantLock cardLock;

    private final BankAccountService bankAccountService;

    private final UserService userService;

    private final UserBankAccountService userBankAccountService;

    private final CardService cardService;

    private final IBANService ibanService;

    @Autowired
    public AccessControllerImpl(BankAccountService bankAccountService, UserService userService, UserBankAccountService userBankAccountService, CardService cardService, IBANService ibanService) {
        this.cardLock = new ReentrantLock();
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.userBankAccountService = userBankAccountService;
        this.cardService = cardService;
        this.ibanService = ibanService;
    }

    @Override
    public OpenedCard provideAccess(String authToken, String iBAN, String username)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        // Get the userId for authentication purposes.
        long userId = AuthenticationHelper.getUserId(authToken);

        // Get the BankAccount from IBAN
        BankAccountBean bankAccountBean;
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
        } catch (NullPointerException e) {
            throw new InvalidParamValueError("iBAN does not exist");
        }

        // Check if user has access
        List<Long> authorizedUsers = bankAccountService.getAuthorizedUsers(bankAccountBean.getBankAccountId());
        if (authorizedUsers.contains(userId) || bankAccountBean.getUserId() == userId) {
            // Check if the call will have effect
            UserBean targetUserAccount = userService.getUserByUsername(username);
            if (targetUserAccount == null) {
                throw new InvalidParamValueError("User does not exist");
            }
            if (authorizedUsers.contains(targetUserAccount.getId()) || bankAccountBean.getUserId() == targetUserAccount.getId()) {
                // User is already authorized, so this won't have effect.
                throw new NoEffectError("User is already authorized");
            } else {
                // Create a new authorization object
                UserBankAccountBean userBankAccountBean = new UserBankAccountBean();
                userBankAccountBean.setBankAccountId(bankAccountBean.getBankAccountId());
                userBankAccountBean.setUserId(targetUserAccount.getId());
                userBankAccountService.saveUserBankAccountBean(userBankAccountBean);

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
                    cardBean.setUserId(targetUserAccount.getId());
                    cardService.saveCardBean(cardBean);
                } finally {
                    cardLock.unlock();
                }
                return new OpenedCard(cardBean);
            }
        } else {
            throw new NotAuthorizedError("Not authorized");
        }
    }

    @Override
    public void revokeAccess(String authToken, String iBAN)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        // Get the BankAccount from IBAN
        BankAccountBean bankAccountBean;
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
        } catch (NullPointerException e) {
            throw new InvalidParamValueError("iBAN does not exist");
        }
        long userId = AuthenticationHelper.getUserId(authToken);

        this.revokeAccess(userId, bankAccountBean.getBankAccountId());
    }

    /**
     * Revokes a user from access to an account.
     * The function can only be called when the user is the main holder of the account.
     *
     * @param authToken
     * @param iBAN
     * @param username
     * @return
     * @throws InvalidParamValueError
     * @throws NotAuthorizedError
     * @throws NoEffectError
     */
    @Override
    public void revokeAccess(String authToken, String iBAN, String username)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {

        long userId = AuthenticationHelper.getUserId(authToken);

        // Get the BankAccount from IBAN
        BankAccountBean bankAccountBean;
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
        } catch (NullPointerException e) {
            throw new InvalidParamValueError("iBAN does not exist");
        }
        if (bankAccountBean.getUserId() != userId) {
            throw new NotAuthorizedError("User is not a owner of the account");
        }

        // Get username of account
        UserBean userBean = userService.getUserByUsername(username);

        // Revoke access to the account
        this.revokeAccess(userBean.getId(), bankAccountBean.getBankAccountId());
    }

    /**
     * Function that revokes access to a user to iBAN.
     *
     * @param userId        The user that needs to be revoked
     * @param bankAccountId The bankAccountId of the account
     */
    public void revokeAccess(long userId, long bankAccountId) throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        userBankAccountService.deleteUserBankAccountBean(userId, bankAccountId);

        // Delete card
        cardService.deleteCardByUserIdAndBankAccount(userId, bankAccountId);
    }
}
