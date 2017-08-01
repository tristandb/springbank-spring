package nl.springbank.controllers.transfer;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.exceptions.InvalidPINError;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.CardService;
import nl.springbank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Sven Konings.
 */
@Service
@AutoJsonRpcServiceImpl
public class TransferControllerImpl implements TransferController {

    private final BankAccountService bankAccountService;

    private final CardService cardService;

    private final TransactionService transactionService;

    @Autowired
    public TransferControllerImpl(BankAccountService bankAccountService, CardService cardService, TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
    }

    @Override
    public void depositIntoAccount(String iBAN, String pinCard, String pinCode, double amount)
            throws InvalidParamValueError, InvalidPINError {
        BankAccountBean bankAccountBean;
        TransactionBean transactionBean;
        // TODO: No source
        try {
            bankAccountBean = bankAccountService.getBankAccountByIban(iBAN);
            CardBean cardBean = cardService.getCard(bankAccountBean.getBankAccountId(), Integer.parseInt(pinCard));
            if (Integer.parseInt(pinCode) != cardBean.getPin()) {
                throw new InvalidPINError();
            }
            transactionBean = new TransactionBean();
            transactionBean.setSourceBankAccount(0);
            transactionBean.setTargetBankAccount(bankAccountBean.getBankAccountId());
            transactionBean.setSourceBankAccountIban("");
            transactionBean.setTargetBankAccountIban(iBAN);
            transactionBean.setTargetName("");
            transactionBean.setAmount(amount);
            transactionBean.setMessage("");
            transactionBean.setDate(Timestamp.from(Instant.now()));
            transactionService.makeTransaction(transactionBean);
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
    }

    @Override
    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount)
            throws InvalidParamValueError, InvalidPINError {
        BankAccountBean sourceAccount;
        BankAccountBean targetAccount;
        TransactionBean transactionBean;
        try {
            sourceAccount = bankAccountService.getBankAccountByIban(sourceIBAN);
            targetAccount = bankAccountService.getBankAccountByIban(targetIBAN);
            CardBean cardBean = cardService.getCard(sourceAccount.getBankAccountId(), Integer.parseInt(pinCard));
            if (Integer.parseInt(pinCode) != cardBean.getPin()) {
                throw new InvalidPINError();
            }
            transactionBean = new TransactionBean();
            transactionBean.setSourceBankAccount(sourceAccount.getBankAccountId());
            transactionBean.setTargetBankAccount(targetAccount.getBankAccountId());
            transactionBean.setSourceBankAccountIban(sourceIBAN);
            transactionBean.setTargetBankAccountIban(targetIBAN);
            transactionBean.setTargetName("");
            transactionBean.setAmount(amount);
            transactionBean.setMessage("");
            transactionBean.setDate(Timestamp.from(Instant.now()));
            transactionService.makeTransaction(transactionBean);
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
    }

    @Override
    public void transferMoney(String authToken, String sourceIBAN, String targetIBAN, String targetName, double amount,
                              String description) throws InvalidParamValueError, NotAuthorizedError {
        long userId = AuthenticationHelper.getUserId(authToken);
        BankAccountBean sourceAccount;
        BankAccountBean targetAccount;
        TransactionBean transactionBean;
        try {
            sourceAccount = bankAccountService.getBankAccountByIban(sourceIBAN);
            targetAccount = bankAccountService.getBankAccountByIban(targetIBAN);
            transactionBean = new TransactionBean();
            transactionBean.setSourceBankAccount(sourceAccount.getBankAccountId());
            transactionBean.setTargetBankAccount(targetAccount.getBankAccountId());
            transactionBean.setSourceBankAccountIban(sourceIBAN);
            transactionBean.setTargetBankAccountIban(targetIBAN);
            transactionBean.setTargetName(targetName);
            transactionBean.setAmount(amount);
            transactionBean.setMessage(description);
            transactionBean.setDate(Timestamp.from(Instant.now()));
            transactionService.makeTransaction(transactionBean);
        } catch (Exception e) {
            throw new InvalidParamValueError(e.getMessage());
        }
        if (sourceAccount.getUserId() != userId && !bankAccountService.getAuthorizedUsers(sourceAccount.getBankAccountId()).contains(userId)) {
            throw new NotAuthorizedError("User is not eligible to get access");
        }
    }
}
