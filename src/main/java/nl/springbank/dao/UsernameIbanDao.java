package nl.springbank.dao;

import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UsernameIbanBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserBankAccountDao. Communicates with the database and returns objects of type
 * <code>nl.springbank.bean.UsernameIbanBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface UsernameIbanDao extends CrudRepository<UsernameIbanBean, Long> {
    Iterable<UsernameIbanBean> findByUserId(long findByUserId);
}
