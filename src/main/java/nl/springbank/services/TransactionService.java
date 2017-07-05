package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.dao.BankAccountDao;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.TransactionDao;
import nl.springbank.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

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

    private final ReentrantLock lock;

    private final BankAccountService bankAccountService;

    /**
     * Autowire <code>TransactionDao</code>
     */
    @Autowired
    public TransactionService(TransactionDao transactionDao, IbanDao ibanDao, BankAccountDao bankAccountDao, BankAccountService bankAccountService) {
        this.transactionDao = transactionDao;
        this.ibanDao = ibanDao;
        this.bankAccountDao = bankAccountDao;
        this.lock = new ReentrantLock(true);
        this.bankAccountService = bankAccountService;
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

    /**
     * Makes a transaction.
     * @param transactionBean
     * @return
     */
    public void makeTransaction(TransactionBean transactionBean) throws TransactionException {
        this.lock.lock();
        try {
            throw new NotImplementedException();
        } finally {
            this.lock.unlock();
        }
    }
}
