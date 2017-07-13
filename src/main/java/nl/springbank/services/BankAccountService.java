package nl.springbank.services;

import com.google.common.collect.Lists;
import nl.springbank.bean.*;
import nl.springbank.dao.BankAccountDao;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.UserBankAccountDao;
import nl.springbank.dao.UsernameIbanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final UsernameIbanDao usernameIbanDao;

    @Autowired
    public BankAccountService(BankAccountDao bankAccountDao, IbanDao ibanDao, UserBankAccountDao userBankAccountDao, UsernameIbanDao usernameIbanDao) {
        this.bankAccountDao = bankAccountDao;
        this.ibanDao = ibanDao;
        this.userBankAccountDao = userBankAccountDao;
        this.usernameIbanDao = usernameIbanDao;
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
     * Returns BankAccountBean given an iBAN.
     *
     * @param iBAN the iBAN
     * @return
     */
    public BankAccountBean getBankAccountByIban(String iBAN) throws NullPointerException {
        IbanBean ibanBean = ibanDao.findByIban(iBAN);
        if (ibanBean == null) {
            throw new NullPointerException();
        }
        return bankAccountDao.findOne(ibanBean.getBankAccountId());
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
     *
     * @param userId
     * @return
     */
    public List<BankAccountBean> getOwnerBankAccounts(long userId) {
        return Lists.newArrayList(bankAccountDao.findByUserId(userId));
    }


    /**
     * Return a list of BankAccounts that the user has access to.
     *
     * @param userId The userId of the user
     */
    public List<UsernameIbanBean> getUserBankAccounts(long userId) {
        // Get a list of BankAccounts that the user has access to or is owner of.
        return Lists.newArrayList(this.usernameIbanDao.findByUserId(userId));
    }

    /**
     * Returns a list of Users that is connected to the BankAccount.
     *
     * @param bankAccountId The bankAccount id
     */
    public List<Long> getAuthorizedUsers(long bankAccountId) {
        List<UserBankAccountBean> userBankAccountBeans = Lists.newArrayList(userBankAccountDao.findByBankAccountId(bankAccountId).iterator());
        List<Long> result = new ArrayList<>();
        for (UserBankAccountBean userBankAccountBean : userBankAccountBeans) {
            result.add(userBankAccountBean.getUserId());
        }
        return result;
    }
}
