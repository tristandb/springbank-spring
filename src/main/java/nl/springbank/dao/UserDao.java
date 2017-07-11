package nl.springbank.dao;

import nl.springbank.bean.UserBean;
import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;

/**
 * UserDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.UserBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface UserDao extends CrudRepository<UserBean, Long> {

    UserBean findByUsernameAndPassword(String username, String password);

    UserBean findByUsername(String username);
}
