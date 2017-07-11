package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
@JsonRpcService("/api/access")
public interface AccessController {
    Object provideAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN,
            @JsonRpcParam(value = "username") String username
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;

    Object revokeAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;

    Object revokeAccess(
            @JsonRpcParam(value = "authToken") String authToken,
            @JsonRpcParam(value = "iBAN") String iBAN,
            @JsonRpcParam(value = "username") String username
    ) throws InvalidParamValueError, NotAuthorizedError, NoEffectError;
}
