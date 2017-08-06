package nl.springbank.controllers.info;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.BalanceObject;
import nl.springbank.objects.BankAccountAccessObject;
import nl.springbank.objects.TransactionObject;
import nl.springbank.objects.UserAccessObject;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.TransactionService;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AutoJsonRpcServiceImpl
public class InfoControllerImpl implements InfoController {

    private final BankAccountService bankAccountService;
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public InfoControllerImpl(BankAccountService bankAccountService, UserService userService, TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @Override
    public BalanceObject getBalance(String authToken, String iBAN) throws InvalidParamValueError, NotAuthorizedError {
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        userService.checkAccess(bankAccount, authToken);
        return new BalanceObject(bankAccount);
    }

    @Override
    public List<TransactionObject> getTransactionsOverview(String authToken, String iBAN, int nrOfTransactions) throws InvalidParamValueError, NotAuthorizedError {
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        userService.checkAccess(bankAccount, authToken);
        List<TransactionBean> transactions = transactionService.getTransactionsBySourceOrTargetAccount(bankAccount, bankAccount);
        return transactions.stream()
                .limit(nrOfTransactions)
                .map(TransactionObject::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAccessObject> getUserAccess(String authToken) throws InvalidParamValueError, NotAuthorizedError {
        UserBean user = userService.getUserByAuth(authToken);
        Set<BankAccountBean> accessAccounts = user.getAccessAccounts();
        return accessAccounts.stream()
                .map(UserAccessObject::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BankAccountAccessObject> getBankAccountAccess(String authToken, String iBAN) throws InvalidParamValueError, NotAuthorizedError {
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        userService.checkHolder(bankAccount, authToken);
        Set<UserBean> accessUsers = bankAccount.getAccessUsers();
        return accessUsers.stream()
                .map(BankAccountAccessObject::new)
                .collect(Collectors.toList());
    }
}
