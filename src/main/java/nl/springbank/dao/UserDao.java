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

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email The user email
     */
    UserBean findByEmail(String email);

    /**
     * Return the user having the passed iban or null if no user is found.
     *
     * @param iban The user iban
     */
    // UserBean findByIbanBean_Iban(String iban);
}
