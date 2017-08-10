package nl.springbank.controllers.info;

import nl.springbank.controllers.BaseControllerTest;
import nl.springbank.objects.AuthTokenObject;
import nl.springbank.objects.IbanAuthTokenObject;
import nl.springbank.objects.TransactionsOverviewObject;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sven Konings
 */
public class InfoControllerTest extends BaseControllerTest {
    @Test
    public void getBalance() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(4, "NL83SPRI0114480386");
        postObject("/api/info", "getBalance", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.balance").exists());
    }

    @Test
    public void getBalanceAuthorizedAccount() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(3, "NL83SPRI0114480386");
        postObject("/api/info", "getBalance", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.balance").exists());
    }

    @Test
    public void getNonExistingBalance() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(4, "NL58INGB8290060130");
        postObject("/api/info", "getBalance", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void getBalanceNotAuthorized() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(2, "NL83SPRI0114480386");
        postObject("/api/info", "getBalance", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void getTransactionsOverview() throws Exception {
        TransactionsOverviewObject transactionsOverviewObject = new TransactionsOverviewObject(4, "NL83SPRI0114480386", 10);
        postObject("/api/info", "getTransactionsOverview", transactionsOverviewObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result").isNotEmpty());
    }

    @Test
    public void getTransactionsOverviewNotAuthorized() throws Exception {
        TransactionsOverviewObject transactionsOverviewObject = new TransactionsOverviewObject(2, "NL83SPRI0114480386", 10);
        postObject("/api/info", "getTransactionsOverview", transactionsOverviewObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void getTransactionsOverviewInvalidIban() throws Exception {
        TransactionsOverviewObject transactionsOverviewObject = new TransactionsOverviewObject(4, "NL58INGB8290060130", 10);
        postObject("/api/info", "getTransactionsOverview", transactionsOverviewObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void getUserAccess() throws Exception {
        AuthTokenObject authTokenObject = new AuthTokenObject(3);
        postObject("/api/info", "getUserAccess", authTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result").isNotEmpty());
    }

    @Test
    public void getUserAccessNoAccounts() throws Exception {
        AuthTokenObject authTokenObject = new AuthTokenObject(5);
        postObject("/api/info", "getUserAccess", authTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void getBankAccountAccess() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(4, "NL83SPRI0114480386");
        postObject("/api/info", "getBankAccountAccess", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result").isNotEmpty());
    }

    @Test
    public void getBankAccountAccessNonOwner() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(3, "NL83SPRI0114480386");
        postObject("/api/info", "getBankAccountAccess", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void getBankAccountAccessInvalidIban() throws Exception {
        IbanAuthTokenObject ibanAuthTokenObject = new IbanAuthTokenObject(4, "NL58INGB8290060130");
        postObject("/api/info", "getBankAccountAccess", ibanAuthTokenObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }
}
