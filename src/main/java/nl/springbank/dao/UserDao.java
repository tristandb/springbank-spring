package nl.springbank.dao;

import nl.springbank.bean.UserBean;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * UserDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.UserBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface UserDao extends CrudRepository<UserBean, Long> {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email The user email
     */
    UserBean findByEmail(String email);
}
