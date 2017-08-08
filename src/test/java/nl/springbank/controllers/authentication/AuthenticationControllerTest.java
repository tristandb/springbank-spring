package nl.springbank.controllers.authentication;

import nl.springbank.controllers.BaseControllerTest;
import nl.springbank.objects.AuthObject;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class AuthenticationControllerTest extends BaseControllerTest {
    @Test
    public void testGetAuthToken() throws Exception {
        AuthObject authObject = new AuthObject("bernard@hinault.fr", "dd");
        postObject("/api/authentication", "getAuthToken", authObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.authToken").exists());
    }

    @Test
    public void testGetAuthTokenInvalidCredentials() throws Exception {
        AuthObject authObject = new AuthObject("bernard@hinault.fr", "ddd");
        postObject("/api/authentication", "getAuthToken", authObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(422));
    }

}
