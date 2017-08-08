package nl.springbank.controllers.account;

import nl.springbank.controllers.BaseControllerTest;
import nl.springbank.objects.AuthTokenObject;
import nl.springbank.objects.IbanAuthTokenObject;
import nl.springbank.objects.OpenAccountObject;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class AccountControllerTest extends BaseControllerTest {
    @Test
    public void openAccount() throws Exception {
        OpenAccountObject openAccountObject = new OpenAccountObject("Test", "Meneer", "T. ", "1989-11-13", "3465653324", "Teststraat", "+31631631631", "test@xyz.com", "test", "meneer");
        postObject("/api/account", "openAccount", openAccountObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.iBAN").exists())
                .andExpect(jsonPath("$.result.pinCard").exists())
                .andExpect(jsonPath("$.result.pinCode").exists());
    }

    @Test
    public void openAdditionalAccount() throws Exception {
        AuthTokenObject authTokenObject = new AuthTokenObject(1);
        postObject("/api/account", "openAdditionalAccount", authTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.iBAN").exists())
                .andExpect(jsonPath("$.result.pinCard").exists())
                .andExpect(jsonPath("$.result.pinCode").exists());
    }

    @Test
    public void closeAccount() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(3, "NL86SPRI0752582963");
        postObject("/api/account", "closeAccount", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isEmpty());
    }
}
