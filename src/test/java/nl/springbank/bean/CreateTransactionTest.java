package nl.springbank.bean;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class CreateTransactionTest {
    private CreateTransaction createTransaction = new CreateTransaction();

    @Test
    public void setTransactionBean() throws Exception {
        TransactionBean transactionBean = new TransactionBean();
        createTransaction.setTransactionBean(transactionBean);
        assertEquals(transactionBean, createTransaction.getTransactionBean());
    }

    @Test
    public void setAuthentication() throws Exception {
        String authentication = "NotARandomString";
        createTransaction.setAuthentication(authentication);
        assertEquals(authentication, createTransaction.getAuthentication());
    }

}