package nl.springbank.dao;

import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBean;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * CardDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.CardBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface CardDao extends CrudRepository<CardBean, Long> {

}
