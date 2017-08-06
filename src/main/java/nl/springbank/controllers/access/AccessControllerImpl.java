package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.OpenedCardObject;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.CardService;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Service
@AutoJsonRpcServiceImpl
public class AccessControllerImpl implements AccessController {

    private final BankAccountService bankAccountService;
    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public AccessControllerImpl(BankAccountService bankAccountService, UserService userService, CardService cardService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.cardService = cardService;
    }

    @Override
    public OpenedCardObject provideAccess(String authToken, String iBAN, String username) throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        userService.checkHolder(bankAccount, authToken);
        UserBean user = userService.getUser(username);
        Set<UserBean> accessUsers = bankAccount.getAccessUsers();
        if (accessUsers.contains(user)) {
            throw new NoEffectError("User already has access");
        }
        accessUsers.add(user);
        bankAccountService.saveBankAccount(bankAccount);
        CardBean card = cardService.newCard(bankAccount, user);
        return new OpenedCardObject(card);
    }

    @Override
    public void revokeAccess(String authToken, String iBAN) throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        UserBean user = userService.getUserByAuth(authToken);
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        if (bankAccount.getHolder().equals(user)) {
            throw new InvalidParamValueError("You can't revoke access to your own account, use close account instead");
        }
        userService.checkAccess(bankAccount, user);
        revokeAccess(bankAccount, user);
    }

    @Override
    public void revokeAccess(String authToken, String iBAN, String username) throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        UserBean holder = userService.getUserByAuth(authToken);
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        userService.checkHolder(bankAccount, holder);
        UserBean user = userService.getUser(username);
        if (holder.equals(user)) {
            throw new InvalidParamValueError("You can't revoke access to your own account, use close account instead");
        }
        revokeAccess(bankAccount, user);
    }

    private void revokeAccess(BankAccountBean bankAccount, UserBean user) throws NoEffectError {
        Set<UserBean> accessUsers = bankAccount.getAccessUsers();
        if (!accessUsers.contains(user)) {
            throw new NoEffectError("The username did not have access");
        }
        accessUsers.remove(user);
        bankAccountService.saveBankAccount(bankAccount);
    }
}
