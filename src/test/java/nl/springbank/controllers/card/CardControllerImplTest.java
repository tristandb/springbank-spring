package nl.springbank.controllers.card;

import nl.springbank.controllers.BaseControllerTest;
import nl.springbank.objects.UnblockCardObject;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tristan de Boer.
 */
public class CardControllerImplTest extends BaseControllerTest {
    @Test
    public void unblockNoEffectCard() throws Exception {
        UnblockCardObject unblockCardObject = new UnblockCardObject(4, "NL83SPRI0114480386", "6095");
        postObject("/api/card", "unblockCard", unblockCardObject)
                .andExpect(jsonPath("$.error.code").value(420));
    }

    @Test
    public void unblockCardNotAuthorized() throws Exception {
        UnblockCardObject unblockCardObject = new UnblockCardObject(2, "NL83SPRI0114480386", "6095");
        postObject("/api/card", "unblockCard", unblockCardObject)
                .andExpect(jsonPath("$.error.code").value(419));
    }
}