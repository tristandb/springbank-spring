package nl.springbank.controllers.info;

import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;

public class InfoControllerImpl implements InfoController {
    @Override
    public Object getBalance(String authToken, String iBAN) throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }

    @Override
    public Object getTransactionsOverview(String authToken, String iBAN, int nrOfTransactions)
            throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }

    @Override
    public Object getUserAccess(String authToken) throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }

    @Override
    public Object getBankAccountAccess(String authToken, String iBAN)
            throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }
}
