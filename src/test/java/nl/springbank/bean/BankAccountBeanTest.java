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
public class BankAccountBeanTest {
    private final BankAccountBean bankAccountBean = new BankAccountBean();

    @Test
    public void testToString() throws Exception {
        assertEquals("BankAccountBean{bankAccountId=0, balance=0.0, ibanBean=null, user=null}", bankAccountBean.toString());
    }

    @Test
    public void setBankAccountId() throws Exception {
        long bankAccountId = 2;
        bankAccountBean.setBankAccountId(bankAccountId);
        assertEquals(bankAccountId, bankAccountBean.getBankAccountId());
    }

    @Test
    public void setBalance() throws Exception {
        double balance = 123.00;
        bankAccountBean.setBalance(balance);
        assertEquals(balance, bankAccountBean.getBalance(), 0);
    }

    @Test
    public void setIbanBean() throws Exception {
        IbanBean ibanBean = new IbanBean();
        bankAccountBean.setIbanBean(ibanBean);
        assertEquals(ibanBean, bankAccountBean.getIbanBean());
    }

    @Test
    public void setUser() throws Exception {
        UserBean userBean = new UserBean();
        bankAccountBean.setUser(userBean);
        assertEquals(userBean, bankAccountBean.getUser());
    }

    @Test
    public void setUsers() throws Exception {
        UserBean userBean = new UserBean();
        UserBean userBean1 = new UserBean();
        Set<UserBean> userSet = new HashSet<>();
        userSet.add(userBean);
        userSet.add(userBean1);
        bankAccountBean.setUsers(userSet);
        assertEquals(userSet, bankAccountBean.getUsers());
    }

    @Test
    public void setUserId() throws Exception {
        int userId = 1;
        bankAccountBean.setUserId(userId);
        assertEquals(userId, bankAccountBean.getUserId());
    }
}