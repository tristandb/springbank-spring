package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.InvalidPINError;
import nl.springbank.exceptions.NotAuthorizedError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests CardService
 * <p>
 * Service is tested on profile 'test', which means 'application-test.properties' is used as configuration.
 * A test is annotated Transactional if changes are made to the database. This ensures a rollback.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CardServiceTest {
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private CardService cardService;

    @Test
    public void testGetCard() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");
        CardBean card = cardService.getCard(bankAccount, "6095");
        assertNotNull(card);
        assertEquals(card.getCardNumber(), "6095");
        assertEquals(card.getBankAccount(), bankAccount);
    }

    @Test
    public void testCheckPin() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");
        cardService.checkPin(bankAccount, "6095", "6957");
    }

    @Test(expected = InvalidPINError.class)
    public void testCheckInvalidPin() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");
        cardService.checkPin(bankAccount, "6095", "0000");
    }

    @Test
    public void testNewCard() throws Exception {
        BankAccountBean bankAccount = bankAccountService.getBankAccount("NL83SPRI0114480386");
        UserBean user = bankAccount.getHolder();
        CardBean card = cardService.newCard(bankAccount, user);
        assertNotNull(card);
        assertEquals(card.getBankAccount(), bankAccount);
        assertEquals(card.getUser(), user);
        assertEquals(card, cardService.getCard(bankAccount, card.getCardNumber()));
    }
}
