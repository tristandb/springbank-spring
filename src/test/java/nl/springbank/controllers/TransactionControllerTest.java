package nl.springbank.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import nl.springbank.bean.*;
import nl.springbank.exceptions.TransactionException;
import nl.springbank.helper.AuthObject;
import nl.springbank.helper.GetBalanceObject;
import nl.springbank.helper.JsonRpcRequest;
import nl.springbank.services.BankAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests BankAccounController
 * <p>
 * Controller is tested on profile 'test', which means 'application-test.properties' is used as configuration.
 * A test is annotated Transactional if changes are made to the database. This ensures a rollback.
 *
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class TransactionControllerTest extends TestCase {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BankAccountService bankAccountService;

    /**
     * Test getTransactions.
     */
    @Test
    public void testGetTransactions() throws Exception {
        this.mockMvc.perform(get("/transaction")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Test getTransactionsByAccountId given an bankAccountId.
     */
    @Test
    public void testGetTransactionsByAccountId() throws Exception {
        this.mockMvc.perform(get("/transaction/1")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Test getTransactionsByAccountId given an non existing bankAccountId.
     */
    @Test
    public void testGetTransactionsByNonExistingAccountId() throws Exception {
        this.mockMvc.perform(get("/transaction/9")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/transaction/10")).andExpect(status().is4xxClientError());
    }

    /**
     * Test postTransaction.
     */
    @Test
    @Transactional
    public void testPostTransaction() throws Exception {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        double balance1 = bankAccountService.getBankAccount(1).getBalance();
        double balance2 = bankAccountService.getBankAccount(2).getBalance();
        TransactionBean transactionBean = new TransactionBean();
        transactionBean.setSourceBankAccount(1);
        transactionBean.setTargetBankAccount(2);
        transactionBean.setAmount(13.00);
        transactionBean.setMessage("[Test] Booking");
        CreateTransaction createTransaction = new CreateTransaction();
        createTransaction.setTransactionBean(transactionBean);
        createTransaction.setAuthentication("kaas");
        System.out.println(mapper.writeValueAsString(createTransaction));
        this.mockMvc.perform(
                post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createTransaction))
        ).andExpect(status().is2xxSuccessful());
        Assert.assertEquals(balance1 - 13.00, bankAccountService.getBankAccount(1).getBalance(), 0);
        Assert.assertEquals(balance2 + 13.00, bankAccountService.getBankAccount(2).getBalance(), 0);
    }

    @Test
    public void testGetBalance() throws Exception {
        // Object to send with as params
        GetBalanceObject getBalanceObject = new GetBalanceObject("authToken", "NL17SPRI0466994144");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getBalance", getBalanceObject);
        this.mockMvc.perform(
                post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk());
        // .andDo(mvcResult -> {System.out.println(mvcResult.getResponse());});
    }

    /**
     * Test postTransaction.
     */
    @Test
    @Transactional
    public void testPostHighTransaction() throws Exception {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        TransactionBean transactionBean = new TransactionBean();
        transactionBean.setSourceBankAccount(1);
        transactionBean.setTargetBankAccount(2);
        transactionBean.setAmount(2000000.00);
        transactionBean.setMessage("[Test] Booking");
        CreateTransaction createTransaction = new CreateTransaction();
        createTransaction.setTransactionBean(transactionBean);
        createTransaction.setAuthentication("kaas");
        this.mockMvc.perform(
                post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createTransaction))
        ).andExpect(status().is4xxClientError());
    }
}
