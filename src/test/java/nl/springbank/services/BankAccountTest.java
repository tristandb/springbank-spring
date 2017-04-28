package nl.springbank.services;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountTest {
    @Autowired
    private BankAccountService bankAccountService;

   /* @Test
    public void exampleTest(){
        try {
            BankAccountBean bankAccounts = bankAccountService.getBankAccount(12);
            assert(bankAccounts == null);
        } catch (Exception e) {
            assert(false);
        }
    }*/
}
