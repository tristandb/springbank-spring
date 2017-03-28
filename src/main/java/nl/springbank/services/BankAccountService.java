package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UserIbanBean;
import nl.springbank.dao.BankAccountDao;
import nl.springbank.dao.IbanDao;
import nl.springbank.dao.UserBankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that does all operation regarding BankAccounts.
 *
 * @author Tristan de Boer.
 */
@Service
public class BankAccountService {
    /**
     * Autowire <code>BankAccountDao</code>
     */
    @Autowired
    private BankAccountDao bankAccountDao;

    @Autowired
    private IbanDao ibanDao;

    @Autowired
    private UserBankAccountDao userBankAccountDao;

    /**
     * Returns a list of bank accounts
     *
     * @return
     */
    public Iterable<BankAccountBean> getBankAccounts() throws Exception {
        return bankAccountDao.findAll();
    }

    /**
     * Return a bank account given a bankAccountId.
     *
     * @param bankAccount The bankAccountId
     * @return
     */
    public BankAccountBean getBankAccount(long bankAccount) throws Exception {
        return bankAccountDao.findOne(bankAccount);
    }

    /**
     * Create a new BankAccount in the database.
     *
     * @param bankAccountBean
     * @return
     */
    public BankAccountBean saveBankAccount(BankAccountBean bankAccountBean) throws Exception {
        return bankAccountDao.save(bankAccountBean);
    }

    /**
     * Deletes a BankAccount.
     * @param bankAccountId The BankAccount to delete
     */
    public void deleteBankAccount(long bankAccountId) throws Exception {
        bankAccountDao.delete(bankAccountId);
    }

    /**
     * Save <code>nl.springbank.bean.UserBankAccountBean</code>
     *
     * @param userBankAccountBean The object to save.
     * @return
     */
    public UserBankAccountBean connectUser(UserBankAccountBean userBankAccountBean){
        // Save the UserBankAccountBean
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
}
