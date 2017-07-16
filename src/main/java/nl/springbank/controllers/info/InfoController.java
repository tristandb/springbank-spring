package nl.springbank.controllers.info;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.BalanceObject;
import nl.springbank.objects.TransactionObject;

import java.util.List;

/**
 * @author Sven Konings.
 */
@JsonRpcService("/api/info")
public interface InfoController {

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419)
    })
    BalanceObject getBalance(
            @JsonRpcParam("authToken") String authToken,
            @JsonRpcParam("iBAN") String iBAN
    ) throws InvalidParamValueError, NotAuthorizedError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419)
    })
    List<TransactionObject> getTransactionsOverview(
            @JsonRpcParam("authToken") String authToken,
            @JsonRpcParam("iBAN") String iBAN,
            @JsonRpcParam("nrOfTransactions") int nrOfTransactions
    ) throws InvalidParamValueError, NotAuthorizedError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419)
    })
    Object getUserAccess(
            @JsonRpcParam("authToken") String authToken
    ) throws InvalidParamValueError, NotAuthorizedError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419)
    })
    Object getBankAccountAccess(
            @JsonRpcParam("authToken") String authToken,
            @JsonRpcParam("iBAN") String iBAN
    ) throws InvalidParamValueError, NotAuthorizedError;
}
