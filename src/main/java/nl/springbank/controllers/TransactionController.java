package nl.springbank.controllers;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Returns a list of <code>nl.springbank.bean.TransactionBean</code>
     *//*
    @ApiOperation(value = "Return Transactions")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactions() {
        Iterable<TransactionBean> transactionBeans = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionBeans);
    }*/

    /**
     * Returns a list of <code>nl.sprinbank.bean.TransactionBean</code> given a bankAccount.
     *//*
    @ApiOperation(value = "Return Transactions given BankAccountId")
    @ResponseBody
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionsByAccountId(@PathVariable String accountId) {
        Iterable<TransactionBean> transactionBeans = transactionService.getTransactionsById(Long.parseLong(accountId));
        if (Iterators.size(transactionBeans.iterator()) > 0) {
            return ResponseEntity.ok(transactionBeans);
        } else {
            return ResponseEntity.status(404).build();
        }
    }*/

    /**
     * Retrieves the balance of a bank account.
     * @param authToken The authentication token, obtained with getAuthToken
     * @param iBAN The number of the bank account
     * @return Balance of the bank account
     * @throws InvalidParamValueError: One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError: The authenticated user is not authorized to perform this action.
     */
    public String getBalance(@JsonRpcParam(value = "authToken") String authToken, @JsonRpcParam(value = "iBAN") String iBAN){
        return "aondsf";
    }

    /**
     * Makes a new transaction.
     *//*
    @ApiOperation(value = "Make a transaction")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @Transactional(isolation = REPEATABLE_READ)
    public ResponseEntity<?> postTransaction(@RequestBody CreateTransaction createTransaction) {
        try {
            // Authentication
            if (!createTransaction.getAuthentication().equals("kaas")){
                return ResponseEntity.status(401).build();
            }

            transactionService.makeTransaction(createTransaction.getTransactionBean());
            return ResponseEntity.ok().build();
        } catch (TransactionException e) {
            return ResponseEntity.badRequest().build();
        }
    }*/
}
