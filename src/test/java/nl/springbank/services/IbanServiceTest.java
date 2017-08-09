package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.IbanBean;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.InvalidParamValueError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;


/**
 * Tests IbanService
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
public class IbanServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private IbanService ibanService;

    @Test
    public void testGetIban() throws Exception {
        assertNotNull(ibanService.getIban("NL83SPRI0114480386"));
    }

    @Test(expected = InvalidParamValueError.class)
    public void testGetInvalidIban() throws Exception {
        ibanService.getIban("NL58INGB8290060130");
    }

    @Test
    public void testNewIban() throws Exception {
        UserBean user = userService.getUser(1);
        BankAccountBean bankAccount = bankAccountService.newBankAccount(user);
        IbanBean iban = ibanService.newIban(bankAccount);
        assertNotNull(iban);
        assertEquals(iban.getBankAccount(), bankAccount);
        assertEquals(iban, ibanService.getIban(iban.getIbanId()));
    }
}
