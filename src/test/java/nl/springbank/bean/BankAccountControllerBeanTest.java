package nl.springbank.bean;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class BankAccountControllerBeanTest {
    private final BankAccountBean bankAccountBean = new BankAccountBean();

    @Test
    public void testToString() throws Exception {
        assertEquals("BankAccountBean{bankAccountId=null, balance=0.0, ibanBean=null, user=null}", bankAccountBean.toString());
    }

    @Test
    public void setBankAccountId() throws Exception {
        long bankAccountId = 2;
        bankAccountBean.setBankAccountId(bankAccountId);
        assertEquals(bankAccountId, bankAccountBean.getBankAccountId().longValue());
    }

    @Test
    public void setBalance() throws Exception {
        double balance = 123.00;
        bankAccountBean.setBalance(balance);
        assertEquals(balance, bankAccountBean.getBalance(), 0);
    }

    @Test
    public void setUser() throws Exception {
        UserBean userBean = new UserBean();
        bankAccountBean.setUser(userBean);
        assertEquals(userBean, bankAccountBean.getUser());
    }


    @Test
    public void setUserId() throws Exception {
        long userId = 1;
        bankAccountBean.setUserId(userId);
        assertEquals(userId, bankAccountBean.getUserId().longValue());
    }
}