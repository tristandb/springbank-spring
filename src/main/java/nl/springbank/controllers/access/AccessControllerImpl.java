package nl.springbank.controllers.access;

import com.googlecode.jsonrpc4j.JsonRpcParam;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
public class AccessControllerImpl implements AccessController {

    @Override
    public Object provideAccess(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN, @JsonRpcParam(value = "username") String username) {
        return null;
    }

    @Override
    public Object revokeAccess(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN, @JsonRpcParam(value = "username") String username) {
        return null;
    }

    @Override
    public Object revokeAccess(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN) {
        return null;
    }
}
