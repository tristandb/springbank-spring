package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.TransactionBean;
import nl.springbank.dao.TransactionDao;
import nl.springbank.exceptions.InvalidParamValueError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Service that does all operation regarding Transactions.
 *
 * @author Tristan de Boer.
 */
@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    /**
     * Get the transaction with the given transaction id.
     *
     * @param transactionId the given transaction id
     * @return the transaction
     * @throws InvalidParamValueError if an error occurred or the transaction doesn't exist
     */
    public TransactionBean getTransaction(long transactionId) throws InvalidParamValueError {
        TransactionBean transaction;
        try {
            transaction = transactionDao.findOne(transactionId);
            Assert.notNull(transaction, "Transaction not found");
        } catch (IllegalArgumentException e) {
            throw new InvalidParamValueError(e);
        }
        return transaction;
    }

    /**
     * Get the transactions with the given source or target bank account.
     *
     * @param sourceAccount the given source bank account
     * @param targetAccount the given target bank account
     * @return the list of transactions
     * @throws InvalidParamValueError if an error occurred
     */
    public List<TransactionBean> getTransactionsBySourceOrTargetAccount(BankAccountBean sourceAccount, BankAccountBean targetAccount) throws InvalidParamValueError {
        List<TransactionBean> transactions;
        try {
            transactions = transactionDao.findBySourceBankAccountOrTargetBankAccount(sourceAccount, targetAccount);
        } catch (IllegalArgumentException e) {
            throw new InvalidParamValueError(e);
        }
        return transactions;
    }

    /**
     * Get the transactions with the given source and target bank account.
     *
     * @param sourceAccount the given source bank account
     * @param targetAccount the given target bank account
     * @return the list of transactions
     * @throws InvalidParamValueError if an error occurred
     */
    public List<TransactionBean> getTransactionsBySourceAndTargetAccount(BankAccountBean sourceAccount, BankAccountBean targetAccount) throws InvalidParamValueError {
        List<TransactionBean> transactions;
        try {
            transactions = transactionDao.findBySourceBankAccountAndTargetBankAccount(sourceAccount, targetAccount);
        } catch (IllegalArgumentException e) {
            throw new InvalidParamValueError(e);
        }
        return transactions;
    }

    /**
     * Get all transactions.
     *
     * @return the list of transactions
     */
    public List<TransactionBean> getTransactions() {
        return transactionDao.findAll();
    }

    /**
     * Save the given transaction.
     *
     * @param transaction the given transaction
     * @return the saved transaction
     */
    public TransactionBean saveTransaction(TransactionBean transaction) {
        return transactionDao.save(transaction);
    }

    /**
     * Save the given transactions.
     *
     * @param transactions the given transactions
     * @return the list of saved transactions
     */
    public List<TransactionBean> saveTransactions(Iterable<TransactionBean> transactions) {
        return transactionDao.save(transactions);
    }

    /**
     * Delete the transaction with the given id.
     *
     * @param transactionId the given id
     */
    public void deleteTransaction(long transactionId) {
        transactionDao.delete(transactionId);
    }

    /**
     * Delete the given transaction.
     *
     * @param transaction the given transaction
     */
    public void deleteTransaction(TransactionBean transaction) {
        transactionDao.delete(transaction);
    }

    /**
     * Delete the given transactions.
     *
     * @param transactions the given transactions
     */
    public void deleteTransactions(Iterable<TransactionBean> transactions) {
        transactionDao.delete(transactions);
    }
}
