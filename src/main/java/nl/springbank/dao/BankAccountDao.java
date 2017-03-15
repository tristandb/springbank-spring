package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * BankAccountDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.UserBean</code>.
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface BankAccountDao  extends CrudRepository<BankAccountBean, Long> {

}
