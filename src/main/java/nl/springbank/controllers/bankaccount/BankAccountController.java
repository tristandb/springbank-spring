package nl.springbank.controllers.bankaccount;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.BalanceObject;

/**
 * @author Tristan de Boer.
 */
@JsonRpcService("/api/bankaccount")
public interface BankAccountController {
    BalanceObject getBalance(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN) throws InvalidParamValueError, NotAuthorizedError;
}
