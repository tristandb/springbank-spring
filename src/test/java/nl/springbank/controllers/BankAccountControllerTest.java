package nl.springbank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UserIbanBean;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    /**
     * Test getBankAccounts.
     */
    /*@Test
    public void testGetBankAccounts() throws Exception {
        this.mockMvc.perform(get("/bankaccount")).andExpect(status().is2xxSuccessful());
    }*/

    /**
     * Test getBankAccount given an bankAccountId.
     */
    /*@Test
    public void testGetBankAccount() throws Exception {
        this.mockMvc.perform(get("/bankaccount/1")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/2")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/3")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/4")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/5")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/6")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/7")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/bankaccount/8")).andExpect(status().is2xxSuccessful());
    }*/

    /**
     * Test getBankAccount given an non existing bankAccountId.
     */
    /*@Test
    public void testGetNonExistingBankAccount() throws Exception {
        this.mockMvc.perform(get("/bankaccount/9")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/bankaccount/10")).andExpect(status().is4xxClientError());
    }*/

    /**
     * Test delete BankAccount given an bankAccountId.
     */
   /* @Test
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
    }*/

    /**
     * Test delete BankAccount given an non existing bankAccountId.
     */
    /*@Test
    @Transactional
    public void testDeleteNonExistingBankAccount() throws Exception {
        this.mockMvc.perform(delete("/bankaccount/9")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(delete("/bankaccount/10")).andExpect(status().is4xxClientError());
    }
*/
    /**
     * Test connectUser given a UserBankAccountBean.
     */
    /*@Test
    @Transactional
    public void testConnectUser() throws Exception {
        UserBankAccountBean userBankAccountBean = new UserBankAccountBean();
        userBankAccountBean.setBankAccountId(8);
        userBankAccountBean.setUserId(1);

        this.mockMvc.perform(
                post("/bankaccount/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userBankAccountBean))
        ).andExpect(status().is2xxSuccessful());

        UserBankAccountBean userBankAccountBean1 = new UserBankAccountBean();
        userBankAccountBean1.setBankAccountId(7);
        userBankAccountBean1.setUserId(2);

        this.mockMvc.perform(
                post("/bankaccount/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userBankAccountBean1))
        ).andExpect(status().is2xxSuccessful());
    }*/

    /**
     * Test connectUser given an UserBankAccountBean with invalid values.
     */
    /*@Test
    @Transactional
    public void testConnectInvalidUser() throws Exception {
        UserBankAccountBean userBankAccountBean = new UserBankAccountBean();
        userBankAccountBean.setBankAccountId(9);
        userBankAccountBean.setUserId(1);

        this.mockMvc.perform(
                post("/bankaccount/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userBankAccountBean))
        ).andExpect(status().is4xxClientError());

        UserBankAccountBean userBankAccountBean1 = new UserBankAccountBean();
        userBankAccountBean1.setBankAccountId(7);
        userBankAccountBean1.setUserId(5);

        this.mockMvc.perform(
                post("/bankaccount/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userBankAccountBean1))
        ).andExpect(status().is4xxClientError());
    }*/

    /**
     * Test connectUser given a UserIbanBean.
     */
    /*@Test
    @Transactional
    public void testConnectUserByIban() throws Exception {
        UserIbanBean userIbanBean = new UserIbanBean();
        userIbanBean.setIban("NL15SPRI0749536255");
        userIbanBean.setUserId(1);

        this.mockMvc.perform(
                post("/bankaccount/users/iban")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userIbanBean))
        ).andExpect(status().is2xxSuccessful());

        UserIbanBean userIbanBean1 = new UserIbanBean();
        userIbanBean1.setIban("NL83SPRI0114480386");
        userIbanBean1.setUserId(2);

        this.mockMvc.perform(
                post("/bankaccount/users/iban")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userIbanBean1))
        ).andExpect(status().is2xxSuccessful());
    }*/

    /**
     * Test connectUser given an UserIbanBean with invalid values.
     */
    /*@Test
    @Transactional
    public void testConnectInvalidUserByIban() throws Exception {
        UserIbanBean userIbanBean = new UserIbanBean();
        userIbanBean.setIban("NL15SPRI0749536256");
        userIbanBean.setUserId(1);

        this.mockMvc.perform(
                post("/bankaccount/users/iban")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userIbanBean))
        ).andExpect(status().is4xxClientError());

        UserIbanBean userIbanBean1 = new UserIbanBean();
        userIbanBean1.setIban("NL83SPRI011448038");
        userIbanBean1.setUserId(5);
        this.mockMvc.perform(
                post("/bankaccount/users/iban")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userIbanBean1))
        ).andExpect(status().is4xxClientError());
    }*/
/*
    *//**
     * Test saveBankAccount with invalid values.
     *//*
    @Test
    @Transactional
    public void testSaveBankAccount() throws Exception {
        // Create bank account
        BankAccountBean bankAccountBean = new BankAccountBean();
        IbanBean ibanBean = new IbanBean();
        bankAccountBean.setUserId((long) 1);
        bankAccountBean.setIbanBean(ibanBean);

        System.out.println(mapper.writeValueAsString(bankAccountBean));
        this.mockMvc.perform(
                post("/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bankAccountBean))
        ).andExpect(status().is2xxSuccessful());

        // Create bank account
        BankAccountBean bankAccountBean1 = new BankAccountBean();
        bankAccountBean1.setUserId((long) 4);
        this.mockMvc.perform(
                post("/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bankAccountBean1))
        ).andExpect(status().is2xxSuccessful());
    }*/

    /**
     * Test saveBankAccount with invalid values.
     */
    @Test
    @Transactional
    public void testSaveInvalidBankAccount() throws Exception {
        // Create bank account
        BankAccountBean bankAccountBean = new BankAccountBean();
        this.mockMvc.perform(
                post("/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bankAccountBean))
        ).andExpect(status().is4xxClientError());

        // Create bank account
        BankAccountBean bankAccountBean1 = new BankAccountBean();
        bankAccountBean1.setUserId((long) 5);
        this.mockMvc.perform(
                post("/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bankAccountBean1))
        ).andExpect(status().is4xxClientError());
    }
}
