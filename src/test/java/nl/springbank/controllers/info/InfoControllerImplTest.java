package nl.springbank.controllers.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.helper.AuthObject;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.helper.JsonRpcRequest;
import nl.springbank.objects.AuthTokenObject;
import org.junit.Before;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sven Konings.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class InfoControllerImplTest {
    @Autowired
    MockMvc mockMvc;

    private AuthTokenObject authTokenObject;

    @Before
    public void authenticate() throws Exception {
        authTokenObject = AuthTokenObject.create(1);
    }

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getBalance() throws Exception {

    }

    @Test
    public void getTransactionsOverview() throws Exception {

    }

    @Test
    public void getUserAccess() throws Exception {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getUserAccess", AuthTokenObject.create(1));
        this.mockMvc.perform(
                post("/api/info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("{\"iBAN\":\"NL58INGB8290060132\",\"username\":\"tristan\"}"));
    }

    @Test
    public void getUserAccessAccess() throws Exception {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getUserAccess", AuthTokenObject.create(14));
        this.mockMvc.perform(
                post("/api/info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("{\"iBAN\":\"NL58INGB8290060132\",\"username\":\"dagoduck\"},{\"iBAN\":\"NL53INGB0694441078\",\"username\":\"dagoduck\"}"));
    }

    @Test
    public void getUserAccessNoAccounts() throws Exception {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getUserAccess", AuthTokenObject.create(16));
        this.mockMvc.perform(
                post("/api/info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("[]"));
    }


    @Test
    public void getBankAccountAccess() throws Exception {

    }
}
