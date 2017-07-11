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
import nl.springbank.services.BankAccountService;
import nl.springbank.services.CardService;
import nl.springbank.services.UserBankAccountService;
import nl.springbank.services.UserService;
import org.omg.CORBA.DynAnyPackage.Invalid;
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

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBankAccountService userBankAccountService;

    @Autowired
    private CardService cardService;

    public AccessControllerImpl() {
        this.cardLock = new ReentrantLock();
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
                    cardService.saveCardBean(cardBean);
                } finally {
                    cardLock.unlock();
                }
                return new OpenedCard(String.valueOf(cardBean.getCardNumber()), String.valueOf(cardBean.getPin()));
            }
        } else {
            throw new NotAuthorizedError();
        }
    }

    @Override
    public Object revokeAccess(String authToken, String iBAN)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        long userId = AuthenticationHelper.getUserId(authToken);
        return null;
    }

    @Override
    public Object revokeAccess(String authToken, String iBAN, String username)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        long userId = AuthenticationHelper.getUserId(authToken);

        return null;
    }
}
