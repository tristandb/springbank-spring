package nl.springbank.controllers.bankaccount;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.exceptions.AuthenticationError;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.helper.AuthenticationHelper;
import nl.springbank.objects.BalanceObject;
import nl.springbank.services.BankAccountService;
import nl.springbank.services.IBANService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tristan de Boer.
 */
@Service
@AutoJsonRpcServiceImpl
public class BankAccountControllerImpl implements BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private IBANService iBANService;

    @Override
    public BalanceObject getBalance(String authToken, String iBAN) throws NotAuthorizedError, InvalidParamValueError {
        long userId = AuthenticationHelper.getUserId(authToken);
        BankAccountBean bankAccountBean;
        long bankAccountId;
        try {
            bankAccountId = iBANService.getIbanBean(iBAN).getBankAccountId();
            bankAccountBean = bankAccountService.getBankAccount(bankAccountId);
        } catch (Exception e) {
            throw new InvalidParamValueError("");
        }
        if (bankAccountBean.getUserId() != userId && !bankAccountService.getAuthorizedUsers(bankAccountId).contains(userId)) {
            throw new NotAuthorizedError();
        }
        return new BalanceObject(bankAccountBean.getBalance());
    }
}
