package nl.springbank.services;

import javassist.NotFoundException;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.UserBean;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.UserDao;
import org.springframework.stereotype.Service;

/**
 * Service that does all operation regarding Users.
 *
 * @author Tristan de Boer.
 */
@Service
public class UserService {

    private final UserDao userDao;

    private final IbanDao ibanDao;

    private final String SUPER_SECRET_KEY = "kaas";

    /**
     * Autowire <code>nl.springbank.bean.UserDao</code>
     */
    public UserService(UserDao userDao, IbanDao ibanDao) {
        this.userDao = userDao;
        this.ibanDao = ibanDao;
    }

    /**
     * Returns a list of <code>nl.springbank.bean.UserBean</code>.
     */
    public Iterable<UserBean> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * Returns a <code>nl.springbank.bean.UserBean</code> having provided an userId.
     */
    public UserBean getUser(long userId) {
        return userDao.findOne(userId);
    }

    /**
     * Creates a new entry for <code>nl.springbank.bean.UserBean</code>.
     */
    public UserBean saveUser(UserBean userBean) {
        return userDao.save(userBean);
    }

    /**
     * Deletes a entry of <code>nl.springbank.bean.UserBean</code> given a userId.
     */
    public void deleteUser(long userId) {
        userDao.delete(userId);
    }


    /**
     * Checks if password is correct.
     * @param username The username of a user.
     * @param password The password of a user.
     * @return
     */
    public boolean isCorrectPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password) != null;
    }

    /**
     * Checks if the user exists in the database.
     * Returns a key if the user exists.
     *
     * @param iban The IBAN to check for.
     * @return
     */
    public String authenticateUser(String iban) throws NotFoundException {
        IbanBean ibanBean = ibanDao.findByIban(iban);
        if (ibanBean != null) {
            return SUPER_SECRET_KEY;
        } else {
            throw new NotFoundException("IBAN not found");
        }
    }
}
