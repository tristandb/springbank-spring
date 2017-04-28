package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.dao.BankAccountDao;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.TransactionDao;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service that does all operation regarding Transactions.
 *
 * @author Tristan de Boer.
 */
@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    private final IbanDao ibanDao;

    private final BankAccountDao bankAccountDao;

    /**
     * Autowire <code>TransactionDao</code>
     */
    public TransactionService(TransactionDao transactionDao, IbanDao ibanDao, BankAccountDao bankAccountDao) {
        this.transactionDao = transactionDao;
        this.ibanDao = ibanDao;
        this.bankAccountDao = bankAccountDao;
    }

    /**
     * Returns a list of all transactions.
     *
     * @return
     */
    public Iterable<TransactionBean> getAllTransactions() {
        return transactionDao.findAll();
    }

    /**
     * Returns a list of all transactions based on IBAN.
     */
    public Iterable<TransactionBean> getTransactionsByIban(String iban) {
        IbanBean ibanBeanByIban = ibanDao.findByIban(iban);
        return transactionDao.findBySourceBankAccountOrTargetBankAccountOrSourceBankAccountIbanOrTargetBankAccountIban(
                ibanBeanByIban.getBankAccountId(), ibanBeanByIban.getBankAccountId(), iban, iban
        );
    }

    /**
     * Returns a list of all transactions based on accountId.
     */
    public Iterable<TransactionBean> getTransactionsById(long accountId) {
        return transactionDao.findBySourceBankAccountOrTargetBankAccount(accountId, accountId);
    }

    public boolean doTransaction(TransactionBean transactionBean) {
        BankAccountBean sourceAccount = bankAccountDao.findByIban(transactionBean.getSourceBankAccountIban());
        BankAccountBean targetAccount = bankAccountDao.findByIban(transactionBean.getTargetBankAccountIban());
        double amount = transactionBean.getAmount();
        if (!(sourceAccount.getBalance() > amount)) {
            return false;
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
        bankAccountDao.save(Arrays.asList(sourceAccount, targetAccount));
        transactionDao.save(transactionBean);
        return true;
    }
}
