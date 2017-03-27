package nl.springbank.services;

import nl.springbank.bean.TransactionBean;
import nl.springbank.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that does all operation regarding Transactions.
 *
 * @author Tristan de Boer.
 */
@Service
public class TransactionService {
    /**
     * Autowire <code>TransactionDao</code>
     */
    @Autowired
    private TransactionDao transactionDao;

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
        return transactionDao.findBySourceBankAccountIban(iban);
    }
}
