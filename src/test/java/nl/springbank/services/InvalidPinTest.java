package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.exceptions.InvalidPINError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static nl.springbank.services.CardService.*;

/**
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InvalidPinTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private CardService cardService;

    @Test
    public void testACheckTwoTimesInvalidPin() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");

        for (int i = 0; i < INVALID_LOGIN_THRESHOLD - 1; i++){
            try {
                cardService.checkPin(bankAccount, "6095", "0000");
            } catch (InvalidPINError pinError) {

            }
        }
        cardService.checkPin(bankAccount, "6095", "6957");
    }

    @Test(expected = NoEffectError.class)
    public void testBUnblockNotBlockedCard() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");
        cardService.unblockCard(bankAccount, "6095");
    }

    @Test(expected = NotAuthorizedError.class)
    public void testCCheckThreeTimesInvalidPin() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");

        for (int i = 0; i < INVALID_LOGIN_THRESHOLD; i++){
            try {
                cardService.checkPin(bankAccount, "6095", "0000");
            } catch (InvalidPINError pinError) {

            }
        }
        cardService.checkPin(bankAccount, "6095", "0000");
    }

    @Test
    public void testDUnblockCard() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");
        cardService.unblockCard(bankAccount, "6095");
    }

}
