package nl.springbank.services;

import com.google.common.collect.Lists;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UserIbanBean;
import nl.springbank.dao.BankAccountDao;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.UserBankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that does all operation regarding BankAccounts.
 *
 * @author Tristan de Boer.
 */
@Service
public class BankAccountService {

    private final BankAccountDao bankAccountDao;

    private final IbanDao ibanDao;

    private final UserBankAccountDao userBankAccountDao;

    @Autowired
    public BankAccountService(BankAccountDao bankAccountDao, IbanDao ibanDao, UserBankAccountDao userBankAccountDao) {
        this.bankAccountDao = bankAccountDao;
        this.ibanDao = ibanDao;
        this.userBankAccountDao = userBankAccountDao;
    }

    /**
     * Returns a list of bank accounts
     *
     * @return
     */
    public Iterable<BankAccountBean> getBankAccounts() {
        return bankAccountDao.findAll();
    }

    /**
     * Return a bank account given a bankAccountId.
     *
     * @param bankAccount The bankAccountId
     * @return
     */
    public BankAccountBean getBankAccount(long bankAccount) {
        return bankAccountDao.findOne(bankAccount);
    }

    /**
     * Create a new BankAccountController in the database.
     *
     * @param bankAccountBean
     * @return
     */
    public BankAccountBean saveBankAccount(BankAccountBean bankAccountBean) {
        return bankAccountDao.save(bankAccountBean);
    }

    /**
     * Deletes a BankAccountController.
     *
     * @param bankAccountId The BankAccountController to delete
     */
    public void deleteBankAccount(long bankAccountId) {
        bankAccountDao.delete(bankAccountId);
    }

    /**
     * Save <code>nl.springbank.bean.UserBankAccountBean</code>
     *
     * @param userBankAccountBean The object to save.
     * @return
     */
    public UserBankAccountBean connectUser(UserBankAccountBean userBankAccountBean) {
        return userBankAccountDao.save(userBankAccountBean);
    }


    /**
     * Connects a user given an IBAN and a userID.
     *
     * @param userIbanBean The IBAN and userId
     * @return
     */
    public UserBankAccountBean connectUserByIban(UserIbanBean userIbanBean) {
        // Search for IBAN
        IbanBean ibanBean = ibanDao.findByIban(userIbanBean.getIban());

        // Create a new UserBankAccountBean
        UserBankAccountBean userBankAccountBean = new UserBankAccountBean(userIbanBean.getUserId(), ibanBean.getBankAccountId());

        // Call a function to save the relationship to the database
        return this.connectUser(userBankAccountBean);
    }

    /**
     * Return a list of BankAccounts that the user is owner of.
     * @param userId
     * @return
     */
    public List<BankAccountBean> getUserBankAccounts(long userId) {
        return Lists.newArrayList(bankAccountDao.findByUserId(userId));
    }
}
