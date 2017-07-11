package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.OpenedCard;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
@JsonRpcService("/api/access")
public interface AccessController {
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    OpenedCard provideAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN,
            @JsonRpcParam(value = "username") String username
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    Object revokeAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    Object revokeAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN,
            @JsonRpcParam(value = "username") String username
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;
}
