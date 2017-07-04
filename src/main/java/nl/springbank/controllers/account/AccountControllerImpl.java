package nl.springbank.controllers.account;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.OpenedAccount;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class AccountControllerImpl implements AccountController {
    @Override
    public OpenedAccount openAccount(@JsonRpcParam("name") String name, @JsonRpcParam("surname") String surname, @JsonRpcParam("initials") String initials, @JsonRpcParam("dob") String dob, @JsonRpcParam("ssn") String ssn, @JsonRpcParam("address") String address, @JsonRpcParam("telephoneNumber") String telephoneNumber, @JsonRpcParam("email") String email, @JsonRpcParam("username") String username, @JsonRpcParam("password") String password) {
        throw new NotImplementedException();
    }

    /**
     * Open an additional account for an existing customer. Also creates a PIN card for this account.
     *
     * @param authToken The authentication token, obtained with getAuthToken.
     * @return A dictionary containing details about the new account.
     */
    @Override
    public OpenedAccount openAdditionalAccount(@JsonRpcParam("authToken") String authToken) throws NotAuthorizedError {
        throw new NotImplementedException();
    }
}
