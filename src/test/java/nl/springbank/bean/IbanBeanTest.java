package nl.springbank.bean;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class IbanBeanTest {
    private IbanBean ibanBean = new IbanBean();

    @Test
    public void setBankAccountId() throws Exception {
        long bankAccountId = 2304;
        ibanBean.setBankAccountId(bankAccountId);
        assertEquals(bankAccountId, ibanBean.getBankAccountId());
    }

    @Test
    public void setBankAccountBean() throws Exception {
        BankAccountBean bankAccountBean = new BankAccountBean();
        ibanBean.setBankAccountBean(bankAccountBean);
        assertEquals(bankAccountBean, ibanBean.getBankAccountBean());
    }

    @Test
    public void setIban() throws Exception {
        String iban = "STRING";
        ibanBean.setIban(iban);
        assertEquals(iban, ibanBean.getIban());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("IbanBean{bankAccountId=0, iban='null', bankAccountBean=null}", ibanBean.toString());
    }

}