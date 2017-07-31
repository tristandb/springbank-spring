package nl.springbank.services;

import com.google.common.collect.Iterators;
import junit.framework.TestCase;
import nl.springbank.bean.TransactionBean;
import nl.springbank.exceptions.TransactionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Tests TranscationService.
 * <p>
 * Service is tested on profile 'test', which means 'application-test.properties' is used as configuration.
 * A test is annotated Transactional if changes are made to the database. This ensures a rollback.
 *
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class TransactionServiceTest extends TestCase {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    /**
     * Test makeTransaction with multiple threads.
     */
    @Test
    @Transactional
    public void testMakeTransactionThreads() throws Exception {
        int threadCount = 100;
        double balance1 = bankAccountService.getBankAccount(8).getBalance();
        double balance2 = bankAccountService.getBankAccount(6).getBalance();

        ExecutorService es = Executors.newFixedThreadPool(threadCount * 2);
        // Set up all threads
        for (int i = 0; i < threadCount * 2; i++) {
            es.submit(new BankTest(i % 2 == 0));
        }

        // Request to close all threads
        es.shutdown();

        // Await all threads
        try {
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("ExecutorService stopped");
        } catch (InterruptedException e) {

        }

        // Check if the values are still correct
        Assert.assertEquals(balance1, bankAccountService.getBankAccount(8).getBalance(), 0);
        Assert.assertEquals(balance2, bankAccountService.getBankAccount(6).getBalance(), 0);
    }

    /**
     * Test getAllTransactions. There should be 4 of them.
     */
    @Test
    public void testGetAllTransactions() throws Exception {
        Assert.assertNotEquals(0, Iterators.size(transactionService.getAllTransactions().iterator()));
    }

    /**
     * Test getTransactionsByIban.
     */
    @Test
    public void testGetTransactionsByIban() throws Exception {
        Assert.assertEquals(4, Iterators.size(transactionService.getTransactionsByIban("NL17SPRI0466994144").iterator()));
        Assert.assertEquals(4, Iterators.size(transactionService.getTransactionsByIban("NL76SPRI0753158935").iterator()));
        Assert.assertEquals(0, Iterators.size(transactionService.getTransactionsByIban("NL96SPRI0774907669").iterator()));
    }

    /**
     * Test getTransactionsByIban with a false IBAN.
     */
    @Test
    public void testGetTransactionsByFalseIban() throws Exception {
        try {
            transactionService.getTransactionsByIban("NL96SPRI0774907679");
            transactionService.getTransactionsByIban("NL96SPRI0774907678");
            transactionService.getTransactionsByIban("NL34SPRI0774907679");
            fail();
        } catch (Exception e) {

        }
    }

    /**
     * Test getTransactionsById.
     */
    @Test
    public void testGetTransactionsById() throws Exception {
        Assert.assertEquals(4, Iterators.size(transactionService.getTransactionsById(1).iterator()));
        Assert.assertEquals(4, Iterators.size(transactionService.getTransactionsById(2).iterator()));
        Assert.assertEquals(0, Iterators.size(transactionService.getTransactionsById(4).iterator()));
    }

    /**
     * Test makeTransaction.
     */
    @Test
    @Transactional
    public void testMakeTransaction() throws Exception {

        double balance1 = bankAccountService.getBankAccount(1).getBalance();
        double balance2 = bankAccountService.getBankAccount(2).getBalance();
        TransactionBean transactionBean = new TransactionBean();
        transactionBean.setSourceBankAccount(1);
        transactionBean.setTargetBankAccount(2);
        transactionBean.setAmount(13.00);
        transactionBean.setMessage("[Test] Booking");
        try {
            transactionService.makeTransaction(transactionBean);
            Assert.assertEquals(balance1 - 13.00, bankAccountService.getBankAccount(1).getBalance(), 0);
            Assert.assertEquals(balance2 + 13.00, bankAccountService.getBankAccount(2).getBalance(), 0);
        } catch (TransactionException e) {
            fail();
        }
    }

    /**
     * Test makeTransaction with too high amount.
     */
    @Test
    @Transactional
    public void testMakeTransactionHighAmount() throws Exception {
        TransactionBean transactionBean = new TransactionBean();
        transactionBean.setSourceBankAccount(1);
        transactionBean.setTargetBankAccount(2);
        transactionBean.setAmount(200000);
        transactionBean.setMessage("[Test] Booking");
        try {
            transactionService.makeTransaction(transactionBean);
            fail();
        } catch (TransactionException e) {
        }
    }

    class BankTest implements Runnable {


        private final boolean reverse;

        BankTest(boolean reverse) {
            this.reverse = reverse;
        }

        @Override
        public void run() {
            if (reverse) {
                TransactionBean transactionBean = new TransactionBean();
                transactionBean.setSourceBankAccount(8);
                transactionBean.setTargetBankAccount(6);
                transactionBean.setAmount(1.00);
                transactionBean.setMessage("[Test] Reverse Booking");
                try {
                    transactionService.makeTransaction(transactionBean);

                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }
            } else {
                TransactionBean transactionBean = new TransactionBean();
                transactionBean.setSourceBankAccount(6);
                transactionBean.setTargetBankAccount(8);
                transactionBean.setAmount(1.00);
                transactionBean.setMessage("[Test] Booking");
                try {
                    transactionService.makeTransaction(transactionBean);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }
            }
        }
    }
}