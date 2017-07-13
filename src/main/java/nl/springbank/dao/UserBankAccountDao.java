package nl.springbank.dao;

import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UsernameIbanBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

/**
 * UserBankAccountDao. Communicates with the database and returns objects of type
 * <code>nl.springbank.bean.UserBankAccountBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface UserBankAccountDao extends CrudRepository<UserBankAccountBean, Long> {
    Iterable<UserBankAccountBean> findByBankAccountId(long bankAccountId);

    void deleteByUserIdAndBankAccountId(long userId, long bankAccountId);
}
