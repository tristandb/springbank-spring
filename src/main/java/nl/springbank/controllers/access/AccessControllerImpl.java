package nl.springbank.controllers.access;

import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
public class AccessControllerImpl implements AccessController {

    @Override
    public Object provideAccess(String authToken, String iBAN, String username)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        return null;
    }

    @Override
    public Object revokeAccess(String authToken, String iBAN)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        return null;
    }

    @Override
    public Object revokeAccess(String authToken, String iBAN, String username)
            throws InvalidParamValueError, NotAuthorizedError, NoEffectError {
        return null;
    }
}
