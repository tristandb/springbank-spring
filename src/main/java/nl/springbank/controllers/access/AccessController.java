package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
@JsonRpcService("/api/access")
public interface AccessController {
    Object provideAccess(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN, @JsonRpcParam(value = "username") String username);

    Object revokeAccess(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN, @JsonRpcParam(value = "username") String username);

    Object revokeAccess(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN);
}
