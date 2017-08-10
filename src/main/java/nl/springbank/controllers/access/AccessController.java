package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.OpenedCardObject;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@JsonRpcService("/api/access")
public interface AccessController {
    /**
     * A customer can share access to one of his bank accounts with another customer. This
     * also creates a corresponding PIN card.
     *
     * @param authToken The authentication token, obtained with getAuthToken
     * @param iBAN      The number of the bank account
     * @param username  The username of customer that should get access
     * @return A dictionary containing the following members:
     * <ul>
     * <li><b>pinCard</b> The number of the created pin card</li>
     * <li><b>pinCode</b> The pin code of the created pin card</li>
     * </ul>
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError     The authenticated user is not authorized to perform this action.
     * @throws NoEffectError          The action has no effect. See message. (e.g. access has already been
     *                                given)
     */
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    OpenedCardObject provideAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN,
            @JsonRpcParam(value = "username") String username
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;

    /**
     * A customer can revoke his access to a bank account if he is not the primary holder,
     * otherwise the customer can revoke another customers access.
     *
     * @param authToken The authentication token, obtained with getAuthToken
     * @param iBAN      The number of the bank account
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError     The authenticated user is not authorized to perform this action.
     * @throws NoEffectError          The action has no effect. See message. (e.g. username did not have
     *                                access)
     */
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    void revokeAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;

    /**
     * A customer can revoke his access to a bank account if he is not the primary holder,
     * otherwise the customer can revoke another customers access.
     *
     * @param authToken The authentication token, obtained with getAuthToken
     * @param iBAN      The number of the bank account
     * @param username  The username of customer whoms access should be revoked
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError     The authenticated user is not authorized to perform this action.
     * @throws NoEffectError          The action has no effect. See message. (e.g. username did not have
     *                                access)
     */
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    void revokeAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN,
            @JsonRpcParam(value = "username") String username
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;
}
