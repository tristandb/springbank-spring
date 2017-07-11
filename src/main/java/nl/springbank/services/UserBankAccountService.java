package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.dao.UserBankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tristan de Boer.
 */
@Service
public class UserBankAccountService {
    @Autowired
    private UserBankAccountDao userBankAccountDao;

    /**
     * Create a new UserBankAccount entry in the database.
     *
     * @param userBankAccountBean
     * @return
     */
    public UserBankAccountBean saveUserBankAccountBean(UserBankAccountBean userBankAccountBean) {
        return userBankAccountDao.save(userBankAccountBean);
    }
}
