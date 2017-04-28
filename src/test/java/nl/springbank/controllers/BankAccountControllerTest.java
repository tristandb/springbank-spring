package nl.springbank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class BankAccountControllerTest extends TestCase {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    private BankAccountController bankAccountController;

    /**
     * Test getBankAccounts.
     */
    @Test
    public void testGetBankAccounts() throws Exception {
        this.mockMvc.perform(get("/bankaccount")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Test getBankAccount given an bankAccountId.
     */
    @Test
    public void testGetBankAccount() throws Exception {
        this.mockMvc.perform(get("/bankaccount/1")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/2")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/3")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/4")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/5")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/6")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/7")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/8")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Test getBankAccount given an non existing bankAccountId.
     */
    @Test
    public void testGetNonExistingBankAccount() throws Exception {
        this.mockMvc.perform(get("/bankaccount/9")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/bankaccount/10")).andExpect(status().is4xxClientError());
    }

    /**
     * Test delete BankAccount given an bankAccountId.
     */
    @Test
    @Transactional
    public void testDeleteBankAccount() throws Exception {
        this.mockMvc.perform(delete("/bankaccount/1")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/2")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/3")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/4")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/5")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/6")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/7")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(delete("/bankaccount/8")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Test delete BankAccount given an non existing bankAccountId.
     */
    @Test
    @Transactional
    public void testDeleteNonExistingBankAccount() throws Exception {
        this.mockMvc.perform(delete("/bankaccount/9")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(delete("/bankaccount/10")).andExpect(status().is4xxClientError());
    }
}
