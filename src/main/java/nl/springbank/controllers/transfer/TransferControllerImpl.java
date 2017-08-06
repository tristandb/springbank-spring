package nl.springbank.controllers.transfer;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.exceptions.InvalidPINError;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.UserHelper;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.CardService;
import nl.springbank.services.TransactionService;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sven Konings.
 */
@Service
@AutoJsonRpcServiceImpl
public class TransferControllerImpl implements TransferController {

    private final BankAccountService bankAccountService;
    private final UserService userService;
    private final CardService cardService;
    private final TransactionService transactionService;

    @Autowired
    public TransferControllerImpl(BankAccountService bankAccountService, UserService userService, CardService cardService, TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.cardService = cardService;
        this.transactionService = transactionService;
    }

    @Override
    public void depositIntoAccount(String iBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINError {
        // TODO: Add ATM account
        BankAccountBean sourceAccount = bankAccountService.getBankAccount(0);
        BankAccountBean targetAccount = bankAccountService.getBankAccount(iBAN);
        cardService.checkPin(targetAccount, pinCard, pinCode);
        String targetName = UserHelper.getDisplayName(targetAccount.getHolder());
        transactionService.makeTransaction(sourceAccount, targetAccount, targetName, amount, "PIN transaction");
    }

    @Override
    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount) throws InvalidParamValueError, InvalidPINError {
        BankAccountBean sourceAccount = bankAccountService.getBankAccount(sourceIBAN);
        cardService.checkPin(sourceAccount, pinCard, pinCode);
        BankAccountBean targetAccount = bankAccountService.getBankAccount(targetIBAN);
        String targetName = UserHelper.getDisplayName(targetAccount.getHolder());
        transactionService.makeTransaction(sourceAccount, targetAccount, targetName, amount, "PIN transaction");
    }

    @Override
    public void transferMoney(String authToken, String sourceIBAN, String targetIBAN, String targetName, double amount, String description) throws InvalidParamValueError, NotAuthorizedError {
        BankAccountBean sourceAccount = bankAccountService.getBankAccount(sourceIBAN);
        userService.checkAccess(sourceAccount, authToken);
        BankAccountBean targetAccount = bankAccountService.getBankAccount(targetIBAN);
        transactionService.makeTransaction(sourceAccount, targetAccount, targetName, amount, description);
    }
}
