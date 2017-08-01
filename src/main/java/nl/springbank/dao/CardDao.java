package nl.springbank.dao;

import nl.springbank.bean.CardBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CardDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.CardBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface CardDao extends CrudRepository<CardBean, Long> {
    Iterable<CardBean> findByBankAccountId(long bankAccountId);

    CardBean findByBankAccountIdAndCardNumber(long bankAccountId, int cardNumber);

    void deleteByUserIdAndBankAccountId(long userId, long bankAccountId);
}
