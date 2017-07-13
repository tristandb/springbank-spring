package nl.springbank.controllers.info;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.UsernameIbanBean;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AutoJsonRpcServiceImpl
public class InfoControllerImpl implements InfoController {

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public Object getBalance(String authToken, String iBAN) throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }

    @Override
    public Object getTransactionsOverview(String authToken, String iBAN, int nrOfTransactions)
            throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }

    /**
     * Retrieves an overview of all bank accounts a user has access to. User can only request this for himself.
     * @param authToken The authentication token, obtained with getAuthToken
     * @return An array of dictionaries containing iBAN and owner.
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError The authenticated user is not authorized to perform this action.
     */
    @Override
    public List<UsernameIbanBean> getUserAccess(String authToken) throws InvalidParamValueError, NotAuthorizedError {
        // Get userId
        long userId = AuthenticationHelper.getUserId(authToken);

        return bankAccountService.getUserBankAccounts(userId);
    }

    @Override
    public Object getBankAccountAccess(String authToken, String iBAN)
            throws InvalidParamValueError, NotAuthorizedError {
        return null;
    }
}
