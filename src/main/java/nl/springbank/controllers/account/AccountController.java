package nl.springbank.controllers.account;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.OpenedAccount;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public interface AccountController {
    OpenedAccount openAccount (@JsonRpcParam("name") String name, @JsonRpcParam("surname") String surname,
                               @JsonRpcParam("initials") String initials, @JsonRpcParam("dob") String dob,
                               @JsonRpcParam("ssn") String ssn, @JsonRpcParam("address") String address,
                               @JsonRpcParam("telephoneNumber") String telephoneNumber, @JsonRpcParam("email") String email,
                               @JsonRpcParam("username") String username, @JsonRpcParam("password") String password);

    OpenedAccount openAdditionalAccount (@JsonRpcParam("authToken") String authToken) throws NotAuthorizedError;
}
