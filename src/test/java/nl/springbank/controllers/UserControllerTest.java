package nl.springbank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.springbank.bean.UserBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.misc.Version.print;

/**
 * Tests UserController
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
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    private UserController userController;

    /**
     * Get a list of users
     *
     * @throws Exception
     */
    @Test
    public void testGetUsers() throws Exception {
        this.mockMvc.perform(get("/user")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Get existing user
     *
     * @throws Exception
     */
    @Test
    public void testGetUserById() throws Exception {
        this.mockMvc.perform(get("/user/1")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Get non-existing user
     *
     * @throws Exception
     */
    @Test
    public void testGetInvalidUserById() throws Exception {
        this.mockMvc.perform(get("/user/5")).andExpect(status().is4xxClientError());
    }

    /**
     * Test user identification
     */
    @Test
    public void testIdentify() throws Exception {
        this.mockMvc.perform(post("/user/identify")
                .contentType(MediaType.APPLICATION_JSON).content("ban@aan.nl")
                .accept(MediaType.APPLICATION_JSON)).
                andExpect(status().is2xxSuccessful());
    }

    /**
     * Test user identification with wrong identification.
     */
    @Test
    public void testWrongIdentify() throws Exception {
        this.mockMvc.perform(post("/user/identify")
                .contentType(MediaType.APPLICATION_JSON).content("not@existing.nl")
                .accept(MediaType.APPLICATION_JSON)).
                andExpect(status().is4xxClientError());
    }

    /**
     * Test user authentication
     */
    @Test
    public void testAuthentication() throws Exception {
        this.mockMvc.perform(post("/user/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("NL17SPRI0466994144")).andExpect(status().is2xxSuccessful());
    }

    /**
     * Test user authentication with wrong IBAN
     */
    @Test
    public void testWrongAuthentication() throws Exception {
        this.mockMvc.perform(post("/user/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("NL17SPRI0466994145")).andExpect(status().is4xxClientError());
    }
}
