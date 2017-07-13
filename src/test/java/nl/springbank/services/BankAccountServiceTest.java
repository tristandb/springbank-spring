package nl.springbank.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class BankAccountServiceTest {
    @Autowired
    private BankAccountService bankAccountService;

    @Test
    public void getUserBankAccounts() throws Exception {
        assertEquals(this.bankAccountService.getUserBankAccounts(1).size(), 1);
        assertEquals(this.bankAccountService.getUserBankAccounts(14).size(), 2);
        assertEquals(this.bankAccountService.getUserBankAccounts(16).size(), 0);
    }
}