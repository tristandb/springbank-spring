package nl.springbank.dao;

import nl.springbank.bean.TransactionBean;
import nl.springbank.bean.UserBean;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * TransactionDao.
 * Communicates with the database and returns objects of type <code>nl.springbank.bean.TransactionBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface TransactionDao extends CrudRepository<TransactionBean, Long> {

}
