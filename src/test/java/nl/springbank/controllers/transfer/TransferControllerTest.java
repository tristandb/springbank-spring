package nl.springbank.controllers.transfer;

import nl.springbank.controllers.BaseControllerTest;
import nl.springbank.objects.DepositObject;
import nl.springbank.objects.PayObject;
import nl.springbank.objects.TransferObject;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sven Konings
 */
public class TransferControllerTest extends BaseControllerTest {
    @Test
    public void depositIntoAccount() throws Exception {
        DepositObject depositObject = new DepositObject("NL83SPRI0114480386", "6095", "6957", 10);
        postObject("/api/transfer", "depositIntoAccount", depositObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    public void depositIntoAccountInvalidIban() throws Exception {
        DepositObject depositObject = new DepositObject("NL58INGB8290060130", "6095", "6957", 10);
        postObject("/api/transfer", "depositIntoAccount", depositObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void depositIntoAccountInvalidCard() throws Exception {
        DepositObject depositObject = new DepositObject("NL83SPRI0114480386", "0000", "6957", 10);
        postObject("/api/transfer", "depositIntoAccount", depositObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void depositIntoAccountInvalidPIN() throws Exception {
        DepositObject depositObject = new DepositObject("NL83SPRI0114480386", "6095", "0000", 10);
        postObject("/api/transfer", "depositIntoAccount", depositObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(421));
    }

    @Test
    public void payFromAccount() throws Exception {
        PayObject payObject = new PayObject("NL15SPRI0749536255", "NL83SPRI0114480386", "9053", "1671", 100000);
        postObject("/api/transfer", "payFromAccount", payObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    public void payFromAccountInvalidSource() throws Exception {
        PayObject payObject = new PayObject("NL58INGB8290060130", "NL83SPRI0114480386", "9053", "1671", 100000);
        postObject("/api/transfer", "payFromAccount", payObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void payFromAccountInvalidTarget() throws Exception {
        PayObject payObject = new PayObject("NL15SPRI0749536255", "NL58INGB8290060130", "9053", "1671", 100000);
        postObject("/api/transfer", "payFromAccount", payObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void payFromAccountInvalidCardNumber() throws Exception {
        PayObject payObject = new PayObject("NL15SPRI0749536255", "NL83SPRI0114480386", "0000", "1671", 100000);
        postObject("/api/transfer", "payFromAccount", payObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void payFromAccountInvalidPIN() throws Exception {
        PayObject payObject = new PayObject("NL15SPRI0749536255", "NL83SPRI0114480386", "9053", "0000", 100000);
        postObject("/api/transfer", "payFromAccount", payObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(421));
    }

    @Test
    public void payFromAccountNotEnoughMoney() throws Exception {
        PayObject payObject = new PayObject("NL83SPRI0114480386", "NL15SPRI0749536255", "6095", "6957", 100000);
        postObject("/api/transfer", "payFromAccount", payObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void transferMoney() throws Exception {
        TransferObject transferObject = new TransferObject(4, "NL15SPRI0749536255", "NL83SPRI0114480386", "Mr. Test", 100000, "Just for you ;)");
        postObject("/api/transfer", "transferMoney", transferObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    public void transferMoneyNotAuthorized() throws Exception {
        TransferObject transferObject = new TransferObject(2, "NL15SPRI0749536255", "NL83SPRI0114480386", "Mr. Test", 100000, "Just for you ;)");
        postObject("/api/transfer", "transferMoney", transferObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void transferMoneyNonExistingUser() throws Exception {
        TransferObject transferObject = new TransferObject(5, "NL15SPRI0749536255", "NL83SPRI0114480386", "Mr. Test", 100000, "Just for you ;)");
        postObject("/api/transfer", "transferMoney", transferObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(419));
    }

    @Test
    public void transferMoneyInvalidSource() throws Exception {
        TransferObject transferObject = new TransferObject(4, "NL58INGB8290060130", "NL83SPRI0114480386", "Mr. Test", 100000, "Just for you ;)");
        postObject("/api/transfer", "transferMoney", transferObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }
    @Test
    public void transferMoneyInvalidTarget() throws Exception {
        TransferObject transferObject = new TransferObject(4, "NL15SPRI0749536255", "NL58INGB8290060130", "Mr. Test", 100000, "Just for you ;)");
        postObject("/api/transfer", "transferMoney", transferObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }

    @Test
    public void transferMoneyNotEnoughMoney() throws Exception {
        TransferObject transferObject = new TransferObject(3, "NL83SPRI0114480386", "NL15SPRI0749536255", "that other guy", 100000, "I actually don't have this much money :(");
        postObject("/api/transfer", "transferMoney", transferObject)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error.code").value(418));
    }
}
