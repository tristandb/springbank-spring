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
    /**
     * Deposit money into a bank account.
     *
     * @param iBAN    The number of the bank account
     * @param pinCard The number of the pin card
     * @param pinCode The code of the pin card
     * @param amount  The amount that is deposited.
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws InvalidPINError        An invalid PINcard, -code or -combination was used.
     */
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

    /**
     * Pay from bank account.
     *
     * @param sourceIBAN The number of the bank account
     * @param targetIBAN The number of the target bank account
     * @param pinCard    The number of the pin card
     * @param pinCode    The code of the pin card
     * @param amount     The amount that is deposited
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws InvalidPINError        An invalid PINcard, -code or -combination was used.
     */
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

    /**
     * Transfer money between two bank accounts.
     *
     * @param authToken   The authentication token, obtained with getAuthToken
     * @param sourceIBAN  The number of the bank account
     * @param targetIBAN  The number of the target bank account
     * @param targetName  The name of the target bank account holder
     * @param amount      The amount that is deposited
     * @param description Description of the transaction
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError     The authenticated user is not authorized to perform this action.
     */
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
