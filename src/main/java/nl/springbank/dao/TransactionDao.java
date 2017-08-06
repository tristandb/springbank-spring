package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.TransactionBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TransactionDao. Communicates with the database and returns objects of type {@link TransactionBean}
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Transactional
public interface TransactionDao extends JpaRepository<TransactionBean, Long> {
    /**
     * Get the transaction with the given source bank account
     *
     * @param sourceAccount the given source bank account
     * @return the list of transactions
     */
    List<TransactionBean> findBySourceBankAccount(BankAccountBean sourceAccount);

    /**
     * Get the transaction with the given target bank account
     *
     * @param targetAccount the given target bank account
     * @return the list of transactions
     */
    List<TransactionBean> findByTargetBankAccount(BankAccountBean targetAccount);

    /**
     * Get the transaction with the given source or target bank account
     *
     * @param sourceAccount the given source bank account
     * @param targetAccount the given target bank account
     * @return the list of transactions
     */
    List<TransactionBean> findBySourceBankAccountOrTargetBankAccount(BankAccountBean sourceAccount, BankAccountBean targetAccount);

    /**
     * Get the transaction with the given source and target bank account
     *
     * @param sourceAccount the given source bank account
     * @param targetAccount the given target bank account
     * @return the list of transactions
     */
    List<TransactionBean> findBySourceBankAccountAndTargetBankAccount(BankAccountBean sourceAccount, BankAccountBean targetAccount);
}
