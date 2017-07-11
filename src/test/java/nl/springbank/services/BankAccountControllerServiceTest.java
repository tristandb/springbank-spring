package nl.springbank.services;

import com.google.common.collect.Iterators;
import junit.framework.TestCase;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UserIbanBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Iterator;

/**
 * Tests BankAccountService
 *
 * Service is tested on profile 'test', which means 'application-test.properties' is used as configuration.
 * A test is annotated Transactional if changes are made to the database. This ensures a rollback.
 *
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class BankAccountControllerServiceTest extends TestCase {
    @Autowired
    private BankAccountService bankAccountService;

    /**
     * Test getBankAccounts. There should be 8 of them.
     */
    @Test
    public void testGetBankAccounts() throws Exception {
        Assert.assertEquals(8, Iterators.size(bankAccountService.getBankAccounts().iterator()));
    }

    /**
     * Test getBankAccount given an bankAccountId.
     */
    @Test
    public void testGetBankAccount() throws Exception {
        Assert.assertEquals(1337, bankAccountService.getBankAccount(1).getBalance(), 0);
        Assert.assertEquals(23329, bankAccountService.getBankAccount(2).getBalance(), 0);
    }

    /**
     * Test getBankAccount given an invalid bankAccountId
     */
    @Test
    public void testGetInvalidBankAccount() throws Exception {
        Assert.assertEquals(null, bankAccountService.getBankAccount(9));
        Assert.assertEquals(null, bankAccountService.getBankAccount(10));
    }

    /**
     * Test delete BankAccountController given an bankAccountId
     */
    @Test
    @Transactional
    public void testDeleteBankAccount() throws Exception {
        Assert.assertNotEquals(null, bankAccountService.getBankAccount(1));
        bankAccountService.deleteBankAccount(1);
        Assert.assertEquals(null, bankAccountService.getBankAccount(1));

        Assert.assertNotEquals(null, bankAccountService.getBankAccount(3));
        bankAccountService.deleteBankAccount(3);
        Assert.assertEquals(null, bankAccountService.getBankAccount(3));
    }

    /**
     * Test connectUser given an userBankAccountBean.
     */
    @Test
    @Transactional
    public void testConnectUser() throws Exception {
        UserBankAccountBean userBankAccountBean = new UserBankAccountBean();
        userBankAccountBean.setBankAccountId(8);
        userBankAccountBean.setUserId(1);

        Assert.assertNotNull(bankAccountService.connectUser(userBankAccountBean));

        UserBankAccountBean userBankAccountBean2 = new UserBankAccountBean();
        userBankAccountBean2.setBankAccountId(7);
        userBankAccountBean2.setUserId(2);

        Assert.assertNotNull(bankAccountService.connectUser(userBankAccountBean2));
    }

    /**
     * Test connectUser given an incorrect userBankAccountBean.
     */
    @Test
    @Transactional
    public void testConnectIncorrectUser() throws Exception {
        // Incorrect BankAccountController
        UserBankAccountBean userBankAccountBean = new UserBankAccountBean();
        userBankAccountBean.setBankAccountId(9);
        userBankAccountBean.setUserId(1);
        try {
            bankAccountService.connectUser(userBankAccountBean);
            fail();
        } catch (Exception ignored) {

        }

        // Incorrect user
        UserBankAccountBean userBankAccountBean2 = new UserBankAccountBean();
        userBankAccountBean2.setBankAccountId(7);
        userBankAccountBean2.setUserId(5);
        try {
            bankAccountService.connectUser(userBankAccountBean2);
            fail();
        } catch (Exception ignored) {

        }
    }

    /**
     * Test connectUser given an UserIbanBean.
     */
    @Test
    @Transactional
    public void testConnectUserIbanBean() throws Exception {
        UserIbanBean userIbanBean = new UserIbanBean();
        userIbanBean.setIban("NL15SPRI0749536255");
        userIbanBean.setUserId(1);

        Assert.assertNotNull(bankAccountService.connectUserByIban(userIbanBean));

        UserIbanBean userIbanBean1 = new UserIbanBean();
        userIbanBean1.setIban("NL83SPRI0114480386");
        userIbanBean1.setUserId(2);

        Assert.assertNotNull(bankAccountService.connectUserByIban(userIbanBean1));
    }


    /**
     * Test connectUser given an incorrect userBankAccountBean.
     */
    @Test
    @Transactional
    public void testConnectIncorrectUserIbanBean() throws Exception {
        // Incorrect BankAccountController
        UserIbanBean userIbanBean = new UserIbanBean();
        userIbanBean.setIban("NL15SPRI0749536256");
        userIbanBean.setUserId(1);
        try {
            bankAccountService.connectUserByIban(userIbanBean);
            fail();
        } catch (Exception ignored) {

        }

        // Incorrect user
        UserIbanBean userIbanBean1 = new UserIbanBean();
        userIbanBean1.setIban("NL83SPRI011448038");
        userIbanBean1.setUserId(5);
        try {
            bankAccountService.connectUserByIban(userIbanBean1);
            fail();
        } catch (Exception ignored) {

        }
    }

    /**
     * Test saveBankAccount
     */
    @Test
    @Transactional
    public void testSaveBankAccount() throws Exception {
        // Create bank account
        BankAccountBean bankAccountBean = new BankAccountBean();
        bankAccountBean.setUserId((long) 1);
        System.out.println(bankAccountBean);
        Assert.assertNotNull(bankAccountService.saveBankAccount(bankAccountBean));

        // Create bank account
        BankAccountBean bankAccountBean1 = new BankAccountBean();
        bankAccountBean1.setUserId((long) 4);
        Assert.assertNotNull(bankAccountService.saveBankAccount(bankAccountBean1));
    }

    /**
     * Test saveBankAccount
     */
    @Test
    @Transactional
    public void testSaveInvalidBankAccount() throws Exception {
        // Create bank account
        try {
            BankAccountBean bankAccountBean = new BankAccountBean();
            bankAccountService.saveBankAccount(bankAccountBean);
            fail();
        } catch (DataIntegrityViolationException e) {

        }

        // Create bank account
        try {
            BankAccountBean bankAccountBean1 = new BankAccountBean();
            bankAccountBean1.setUserId((long) 5);
            bankAccountService.saveBankAccount(bankAccountBean1);
            fail();
        } catch (DataIntegrityViolationException e) {

        }
    }
}
