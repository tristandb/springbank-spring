package nl.springbank.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.springbank.helper.JsonRpcRequest;
import nl.springbank.helper.MultiplyObject;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class JsonRpcTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testMultiplier() throws Exception {
        // Object to send with as params
        MultiplyObject multiplyObject = new MultiplyObject(5, 6);

        // Create a JsonRpcRequest to method "multiply"
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("multiply", multiplyObject);

        // Perform the request
        this.mockMvc.perform(
                post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk())
        .andExpect(content().json("{\"jsonrpc\":\"2.0\",\"id\":\"1\",\"result\":30}"));
    }

    @Test
    public void testMultiplierWithId() throws Exception {
        // Object to send with as params
        MultiplyObject multiplyObject = new MultiplyObject(25, 25);

        // Create a JsonRpcRequest to method "multiply" and an specific id
        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest("multiply", multiplyObject, "123");

        // Perform the request
        this.mockMvc.perform(
                post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(jsonRpcRequest))
        ).andExpect(status().isOk())
                .andExpect(content().json("{\"jsonrpc\":\"2.0\",\"id\":\"123\",\"result\":625}"));
    }
}
