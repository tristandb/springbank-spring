package nl.springbank.controllers.account;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.OpenedAccount;

/**
 * Description
 *
 * @author Tristan de Boer.
 */
@JsonRpcService("/api/account")
public interface AccountController {
    @JsonRpcErrors({@JsonRpcError(exception = InvalidParamValueError.class, code = 418)})
    OpenedAccount openAccount(
            @JsonRpcParam("name") String name,
            @JsonRpcParam("surname") String surname,
            @JsonRpcParam("initials") String initials,
            @JsonRpcParam("dob") String dob,
            @JsonRpcParam("ssn") String ssn,
            @JsonRpcParam("address") String address,
            @JsonRpcParam("telephoneNumber") String telephoneNumber,
            @JsonRpcParam("email") String email,
            @JsonRpcParam("username") String username,
            @JsonRpcParam("password") String password
    ) throws InvalidParamValueError;

    @JsonRpcErrors({@JsonRpcError(exception = NotAuthorizedError.class, code = 419)})
    OpenedAccount openAdditionalAccount(@JsonRpcParam("authToken") String authToken) throws NotAuthorizedError;

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419)
    })
    void closeAccount(
            @JsonRpcParam("authToken") String authToken,
            @JsonRpcParam("iBAN") String iBAN
    ) throws InvalidParamValueError, NotAuthorizedError;
}
