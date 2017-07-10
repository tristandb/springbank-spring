package nl.springbank.controllers.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.springbank.helper.AuthObject;
import nl.springbank.helper.JsonRpcRequest;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer).
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class AuthenticationImplTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetAuthToken() throws Exception {
        // Object to send with as params
        AuthObject authObject = new AuthObject("tristan", "password");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getAuthToken", authObject);
        this.mockMvc.perform(
                post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetAuthTokenInvalidCredentials() throws Exception {
        // Object to send with as params
        AuthObject authObject = new AuthObject("tristan", "wrong_password");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getAuthToken", authObject);
        this.mockMvc.perform(
                post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(content().json("{\"jsonrpc\":\"2.0\",\"id\":\"1\",\"error\":{\"code\":-32001,\"message\":null,\"data\":{\"exceptionTypeName\":\"nl.springbank.exceptions.AuthenticationError\",\"message\":null}}}"));
    }

}