package nl.springbank.controllers.authentication;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.AuthenticationError;
import nl.springbank.objects.AuthenticationObject;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@JsonRpcService("/api/authentication")
public interface Authentication {
    @JsonRpcErrors({@JsonRpcError(exception = AuthenticationError.class, code = 422)})
    AuthenticationObject getAuthToken(
            @JsonRpcParam(value = "username") String username,
            @JsonRpcParam(value = "password") String password
    ) throws AuthenticationError;
}
