package nl.springbank.controllers.transfer;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.exceptions.InvalidPINError;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import org.springframework.stereotype.Service;

/**
 * @author Sven Konings.
 */
@Service
@AutoJsonRpcServiceImpl
public class TransferControllerImpl implements TransferController {
    @Override
    public void depositIntoAccount(String iBAN, String pinCard, String pinCode, double amount)
            throws InvalidParamValueError, InvalidPINError {

    }

    @Override
    public void payFromAccount(String sourceIBAN, String targetIBAN, String pinCard, String pinCode, double amount)
            throws InvalidParamValueError, InvalidPINError {

    }

    @Override
    public void transferMoney(String authToken, String sourceIBAN, String targetIBAN, String targetName, double amount,
                              String description) throws InvalidParamValueError, NotAuthorizedError {

    }
}
