package nl.springbank.controllers.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.springbank.helper.AuthObject;
import nl.springbank.helper.JsonRpcRequest;
import nl.springbank.helper.OpenAccountObject;
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

    @Test
    public void openAccount() throws Exception {
        // Object to send with as params
        OpenAccountObject accountObject = new OpenAccountObject("Duck", "Donald", "D.", "1954-02-19","571376046", "1313 Webfoot Walk, Duckburg",
                "+316 12345678", "donald@gmail.com", "duckd", "kwikkwekkwak");

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("openAccount", accountObject);
        this.mockMvc.perform(
                post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk()).andDo(mvcResult -> {System.out.println(mvcResult.getResponse().getContentAsString());});
    }

    @Test
    public void openAdditionalAccount() throws Exception {

    }

    @Test
    public void closeAccount() throws Exception {

    }

}