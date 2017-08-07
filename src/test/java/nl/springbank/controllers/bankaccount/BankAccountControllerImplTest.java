package nl.springbank.controllers.bankaccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.helper.jsonrpc.JsonRpcRequest;
import nl.springbank.objects.AuthTokenObject;
import nl.springbank.objects.IbanAuthTokenObject;
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

import javax.transaction.Transactional;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class BankAccountControllerImplTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private AuthTokenObject authTokenObject1;
    private AuthTokenObject authTokenObject2;
    private AuthTokenObject authTokenObject14;

    @Before
    public void authenticate() throws Exception {
        authTokenObject1 = new AuthTokenObject(Jwts.builder().setSubject(String.valueOf(1)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthenticationHelper.PRIVATE_KEY).compact());
        authTokenObject2 = new AuthTokenObject(Jwts.builder().setSubject(String.valueOf(2)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthenticationHelper.PRIVATE_KEY).compact());
        authTokenObject14 = new AuthTokenObject(Jwts.builder().setSubject(String.valueOf(14)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthenticationHelper.PRIVATE_KEY).compact());
    }

    @Test
    @Transactional
    public void getBalance() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(authTokenObject1.getAuthToken(), "NL58INGB8290060132");
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getBalance", ibanAuthTokenObject);
        this.mockMvc.perform(
                post("/api/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("{\"balance\":20.0}"));
    }

    @Test
    @Transactional
    public void getBalanceAuthorizedAccount() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(authTokenObject14.getAuthToken(), "NL58INGB8290060132");
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getBalance", ibanAuthTokenObject);
        this.mockMvc.perform(
                post("/api/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("{\"balance\":20.0}"));
    }

    @Test
    @Transactional
    public void getNonExistingBalance() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(authTokenObject1.getAuthToken(), "NL58INGB8290060130");
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getBalance", ibanAuthTokenObject);
        this.mockMvc.perform(
                post("/api/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("InvalidParamValueError"));
    }

    @Test
    @Transactional
    public void getBalanceNotAuthorized() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(authTokenObject2.getAuthToken(), "NL58INGB8290060132");
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("getBalance", ibanAuthTokenObject);
        this.mockMvc.perform(
                post("/api/bankaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("NotAuthorizedError"));
    }
}
