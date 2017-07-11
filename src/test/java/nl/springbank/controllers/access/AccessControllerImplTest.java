package nl.springbank.controllers.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.helper.*;
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

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class AccessControllerImplTest {
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
    public void provideAccess() throws Exception {
        // Object to send with as params
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(authTokenObject.getAuthToken(), "NL58INGB8290060132", "bgates");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("provideAccess", provideAccessObject);
        this.mockMvc.perform(
                post("/api/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("pinCode"));
    }

    @Test
    @Transactional
    public void provideInvalidIbanAccess() throws Exception {
        // Object to send with as params
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(authTokenObject.getAuthToken(), "NL58INGB8290060130", "bgates");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("provideAccess", provideAccessObject);
        this.mockMvc.perform(
                post("/api/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("InvalidParamValueError"));
    }

    @Test
    @Transactional
    public void provideInvalidAccess() throws Exception {
        // Object to send with as params
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(authTokenObject.getAuthToken(), "NL58INGB8290060132", "bhinault");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("provideAccess", provideAccessObject);
        this.mockMvc.perform(
                post("/api/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("InvalidParamValueError"));
    }

    @Test
    @Transactional
    public void provideAccessAlreadyGivenOwner() throws Exception {
        // Object to send with as params
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(authTokenObject.getAuthToken(), "NL58INGB8290060132", "tristan");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("provideAccess", provideAccessObject);
        this.mockMvc.perform(
                post("/api/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("NoEffectError"));
    }

    @Test
    @Transactional
    public void provideAccessAlreadyGiven() throws Exception {
        // Object to send with as params
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(authTokenObject.getAuthToken(), "NL58INGB8290060132", "dagoduck");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("provideAccess", provideAccessObject);
        this.mockMvc.perform(
                post("/api/access")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("NoEffectError"));
    }

    @Test
    public void revokeAccess() throws Exception {

    }

    @Test
    public void revokeAccess1() throws Exception {

    }

}