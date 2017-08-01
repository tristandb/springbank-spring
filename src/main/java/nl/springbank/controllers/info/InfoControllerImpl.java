package nl.springbank.controllers.info;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.bean.UsernameIbanBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.objects.BalanceObject;
import nl.springbank.objects.BankAccountAccessObject;
import nl.springbank.objects.TransactionObject;
import nl.springbank.objects.UserAccessObject;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.TransactionService;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AutoJsonRpcServiceImpl
public class InfoControllerImpl implements InfoController {

    private final BankAccountService bankAccountService;

    private final TransactionService transactionService;

    private final UserService userService;

    @Autowired
    public InfoControllerImpl(BankAccountService bankAccountService, TransactionService transactionService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @Override
    public BalanceObject getBalance(String authToken, String iBAN) throws InvalidParamValueError, NotAuthorizedError {
        long userId = AuthenticationHelper.getUserId(authToken);
        BankAccountBean bankAccountBean;
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
        if (bankAccountBean.getUserId() != userId && !bankAccountService.getAuthorizedUsers(bankAccountBean.getBankAccountId()).contains(userId)) {
            throw new NotAuthorizedError("User is not eligible to get access");
        }
        return new BalanceObject(bankAccountBean);
    }

    @Override
    public List<TransactionObject> getTransactionsOverview(String authToken, String iBAN, int nrOfTransactions)
            throws InvalidParamValueError, NotAuthorizedError {
        long userId = AuthenticationHelper.getUserId(authToken);
        BankAccountBean bankAccountBean;
        Iterable<TransactionBean> transactionBeans;
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
            transactionBeans = transactionService.getTransactionsById(bankAccountBean.getBankAccountId());
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
        if (bankAccountBean.getUserId() != userId && !bankAccountService.getAuthorizedUsers(bankAccountBean.getBankAccountId()).contains(userId)) {
            throw new NotAuthorizedError("User is not eligible to get access");
        }
        List<TransactionObject> transactions = new ArrayList<>();
        for (TransactionBean transactionBean : transactionBeans) {
            if (nrOfTransactions <= 0) {
                break;
            }
            nrOfTransactions--;
            transactions.add(new TransactionObject(transactionBean));
        }
        return transactions;
    }

    /**
     * Retrieves an overview of all bank accounts a user has access to. User can only request this for himself.
     *
     * @param authToken The authentication token, obtained with getAuthToken
     * @return An array of dictionaries containing iBAN and owner.
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError     The authenticated user is not authorized to perform this action.
     */
    @Override
    public List<UserAccessObject> getUserAccess(String authToken) throws InvalidParamValueError, NotAuthorizedError {
        // Get userId
        long userId = AuthenticationHelper.getUserId(authToken);
        List<UsernameIbanBean> usernameIbanBeans;
        try {
            usernameIbanBeans = bankAccountService.getUserBankAccounts(userId);
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
        List<UserAccessObject> userAccessObjects = new ArrayList<>();
        for (UsernameIbanBean usernameIbanBean : usernameIbanBeans) {
            userAccessObjects.add(new UserAccessObject(usernameIbanBean));
        }
        return userAccessObjects;
    }

    @Override
    public Object getBankAccountAccess(String authToken, String iBAN)
            throws InvalidParamValueError, NotAuthorizedError {
        long userId = AuthenticationHelper.getUserId(authToken);
        BankAccountBean bankAccountBean;
        List<Long> authorizedUserIds;
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
            authorizedUserIds = bankAccountService.getAuthorizedUsers(bankAccountBean.getBankAccountId());
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
        if (bankAccountBean.getUserId() != userId && !bankAccountService.getAuthorizedUsers(bankAccountBean.getBankAccountId()).contains(userId)) {
            throw new NotAuthorizedError("User is not eligible to get access");
        }
        List<BankAccountAccessObject> bankAccountAccessObjects = new ArrayList<>();
        for (Long authorizedUserId : authorizedUserIds) {
            bankAccountAccessObjects.add(new BankAccountAccessObject(userService.getUser(authorizedUserId)));
        }
        return bankAccountAccessObjects;
    }
}
