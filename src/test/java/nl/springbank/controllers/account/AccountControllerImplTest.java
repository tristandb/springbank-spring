package nl.springbank.controllers.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.helper.*;
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
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer).
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class AccountControllerImplTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private AuthTokenObject authTokenObject;

    @Before
    public void authenticate() throws Exception {
        authTokenObject = new AuthTokenObject(Jwts.builder().setSubject(String.valueOf(1)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthenticationHelper.PRIVATE_KEY).compact());
    }

    @Test
    @Transactional
    public void openAccount() throws Exception {
        // Object to send with as params
        OpenAccountObject accountObject = new OpenAccountObject("Duck", "Donald", "D.", "1954-02-19","571376046", "1313 Webfoot Walk, Duckburg",
                "+316 12345678", "donald@gmail.com", "duckd", "kwikkwekkwak");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("openAccount", accountObject);
        this.mockMvc.perform(
                post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("iBAN"));
    }

    @Test
    @Transactional
    public void openAdditionalAccount() throws Exception {
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("openAdditionalAccount", authTokenObject);
        this.mockMvc.perform(
                post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("iBAN"));
    }

    @Test
    @Transactional
    public void closeAccount() throws Exception {
        CloseAccount closeAccount = new CloseAccount(authTokenObject.getAuthToken(), "NL58INGB8290060132");
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("closeAccount", closeAccount);
        this.mockMvc.perform(
                post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("{}"));
    }
}
