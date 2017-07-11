package nl.springbank.controllers.transfer;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidPINError;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;

/**
 * @author Sven Konings.
 */
@JsonRpcService("/api/transfer")
public interface TransferController {

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = InvalidPINError.class, code = 421)
    })
    void depositIntoAccount(
            @JsonRpcParam("iBAN") String iBAN,
            @JsonRpcParam("pinCard") String pinCard,
            @JsonRpcParam("pinCode") String pinCode,
            @JsonRpcParam("amount") double amount
    ) throws InvalidParamValueError, InvalidPINError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = InvalidPINError.class, code = 421)
    })
    void payFromAccount(
            @JsonRpcParam("sourceIBAN") String sourceIBAN,
            @JsonRpcParam("targetIBAN") String targetIBAN,
            @JsonRpcParam("pinCard") String pinCard,
            @JsonRpcParam("pinCode") String pinCode,
            @JsonRpcParam("amount") double amount
    ) throws InvalidParamValueError, InvalidPINError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419)
    })
    void transferMoney(
            @JsonRpcParam("authToken") String authToken,
            @JsonRpcParam("sourceIBAN") String sourceIBAN,
            @JsonRpcParam("targetIBAN") String targetIBAN,
            @JsonRpcParam("targetName") String targetName,
            @JsonRpcParam("amount") double amount,
            @JsonRpcParam("description") String description
    ) throws InvalidParamValueError, NotAuthorizedError;
}
