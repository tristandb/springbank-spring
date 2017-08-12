package nl.springbank.controllers.card;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.CardService;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tristan de Boer.
 */
@Service
@AutoJsonRpcServiceImpl
public class CardControllerImpl implements CardController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;

    @Override
    public Object unblockCard(@JsonRpcParam("authToken") String authToken, @JsonRpcParam("iBAN") String iBAN, @JsonRpcParam("pinCard") String pinCard) throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        BankAccountBean bankAccount = bankAccountService.getBankAccount(iBAN);
        userService.checkAccess(bankAccount, authToken);
        cardService.unblockCard(bankAccount, pinCard);
        return new Object();
    }
}
