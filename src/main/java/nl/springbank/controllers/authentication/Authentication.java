package nl.springbank.controllers.authentication;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.objects.AuthenticationObject;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@JsonRpcService("/api/authentication")
public interface Authentication {
    AuthenticationObject getAuthToken (@JsonRpcParam(value = "username") String username, @JsonRpcParam(value = "password") String password);
}
