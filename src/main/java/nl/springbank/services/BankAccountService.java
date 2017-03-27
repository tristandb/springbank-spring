package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.dao.BankAccountDao;
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
     *
     * @param bankAccountId The BankAccount to delete
     */
    public void deleteBankAccount(long bankAccountId) throws Exception {
        bankAccountDao.delete(bankAccountId);
    }
}
