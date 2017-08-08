package nl.springbank.controllers.access;

import nl.springbank.controllers.BaseControllerTest;
import nl.springbank.objects.ProvideAccessObject;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class AccessControllerTest extends BaseControllerTest {
    @Test
    public void provideAccess() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(4, "NL83SPRI0114480386", "sven@konings.nl");
        postObject("/api/access", "provideAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.pinCard").exists())
                .andExpect(jsonPath("$.result.pinCode").exists());
    }

    @Test
    public void provideInvalidAccess() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(3, "NL83SPRI0114480386", "sven@konings.nl");
        postObject("/api/access", "provideAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void provideInvalidIbanAccess() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(4, "NL58INGB8290060130", "sven@konings.nl");
        postObject("/api/access", "provideAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void provideAccessAlreadyGivenOwner() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(4, "NL83SPRI0114480386", "duckd");
        postObject("/api/access", "provideAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(420));
    }

    @Test
    public void provideAccessAlreadyGiven() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(4, "NL83SPRI0114480386", "dagobertduck@gmail.com");
        postObject("/api/access", "provideAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(420));
    }

    @Test
    public void revokeAccess() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(4, "NL83SPRI0114480386", "dagobertduck@gmail.com");
        postObject("/api/access", "revokeAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    public void revokeOwnAccess() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(3, "NL83SPRI0114480386");
        postObject("/api/access", "revokeAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    public void revokeAccessInvalidIban() throws Exception {
        ProvideAccessObject provideAccessObject = new ProvideAccessObject(3, "NL58INGB8290060130");
        postObject("/api/access", "revokeAccess", provideAccessObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

}