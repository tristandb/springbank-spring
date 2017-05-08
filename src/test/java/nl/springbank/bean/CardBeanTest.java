package nl.springbank.bean;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class CardBeanTest {
    private CardBean cardBean = new CardBean();

    @Test
    public void setCardId() throws Exception {
        long cardId = 12;
        cardBean.setCardId(cardId);
        assertEquals(cardId, cardBean.getCardId());
    }

    @Test
    public void setCardNumber() throws Exception {
        int cardNumber = 10;
        cardBean.setCardNumber(cardNumber);
        assertEquals(cardNumber, cardBean.getCardNumber());
    }

    @Test
    public void setBankAccountId() throws Exception {
        int bankAccountId = 42;
        cardBean.setBankAccountId(bankAccountId);
        assertEquals(bankAccountId, cardBean.getBankAccountId());
    }

    @Test
    public void setExpirationDate() throws Exception {
        Date date = new Date((new java.util.Date()).getTime());
        cardBean.setExpirationDate(date);
        assertEquals(date, cardBean.getExpirationDate());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("CardBean{cardId=0, cardNumber=0, bankAccountId=0, expirationDate=null}", cardBean.toString());
        int bankAccountId = 42;
        cardBean.setBankAccountId(bankAccountId);
        int cardNumber = 10;
        cardBean.setCardNumber(cardNumber);
        assertEquals("CardBean{cardId=0, cardNumber=10, bankAccountId=42, expirationDate=null}", cardBean.toString());
    }
}