package nl.springbank.dao;

import nl.springbank.bean.TransactionBean;
import nl.springbank.bean.UserBean;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * TransactionDao.
 * Communicates with the database and returns objects of type <code>nl.springbank.bean.TransactionBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface TransactionDao extends CrudRepository<TransactionBean, Long> {
    /**
     * Finds all TransactionBeans when a IBAN is provided.
     *
     * @param iban
     * @return
     */
    Iterable<TransactionBean> findBySourceBankAccountIban(String iban);

    /**
     * Finds all TransactionBeans (source and target) when a AccountId is provided.
     */
    Iterable<TransactionBean> findBySourceBankAccountOrTargetBankAccount(long sourceAccountId, long targetAccountId);

    /**
     * Finds all TransactionBeans (source and target) when a AccountId and Iban is provided.
     */
    Iterable<TransactionBean> findBySourceBankAccountOrTargetBankAccountOrSourceBankAccountIbanOrTargetBankAccountIban(long sourceAccountId, long targetAccountId, String sourceAccountIdIban, String targetAccountIdIban);
}
