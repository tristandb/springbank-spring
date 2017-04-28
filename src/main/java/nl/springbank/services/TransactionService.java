package nl.springbank.services;

import nl.springbank.bean.CreateTransaction;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.TransactionDao;
import nl.springbank.exceptions.NothAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    @Autowired
    private IbanDao ibanDao;

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

    public void createTransaction(CreateTransaction createTransaction) throws NothAuthorizedException {
        // Check authentication
        if (createTransaction.getAuthentication().equals("kaas")){

            transactionDao.save(createTransaction.getTransactionBean());

            // TODO: Update balance on accounts.
        } else {
            // Authenticating has not succeeded, throw error
            throw new NothAuthorizedException();
        }
    }
}
