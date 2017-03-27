package nl.springbank.services;

import nl.springbank.bean.UserBean;
import nl.springbank.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that does all operation regarding Users.
 *
 * @author Tristan de Boer.
 */
@Service
public class UserService {
    /**
     * Autowire <code>TransactionDao</code>
     */
    @Autowired
    private UserDao userDao;

    /**
     * Returns a list of <code>nl.springbank.bean.UserBean</code>.
     */
    public Iterable<UserBean> getAllUsers() throws Exception {
        return userDao.findAll();
    }

    /**
     * Returns a <code>nl.springbank.bean.UserBean</code> having provided an userId.
     */
    public UserBean getUser(long userId) throws Exception {
        return userDao.findOne(userId);
    }

    /**
     * Creates a new entry for <code>nl.springbank.bean.UserBean</code>.
     */
    public UserBean saveUser(UserBean userBean) throws Exception {
        return userDao.save(userBean);
    }

    /**
     * Deletes a entry of <code>nl.springbank.bean.UserBean</code> given a userId.
     */
    public void deleteUser(long userId) throws Exception {
        userDao.delete(userId);
    }

    /**
     * Returns a user given an email.
     * @param email The email to use.
     */
    public UserBean getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public UserBean getUserByIban(String iban) {
        return userDao.findByIban(iban);

    }
}
