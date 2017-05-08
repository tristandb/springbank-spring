package nl.springbank.controllers;

import com.google.common.collect.Iterators;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.TransactionBean;
import nl.springbank.exceptions.TransactionException;
import nl.springbank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@Api(value = "transaction", description = "Manage Transaction.")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Returns a list of <code>nl.springbank.bean.TransactionBean</code>
     */
    @ApiOperation(value = "Return Transactions")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactions() {
        Iterable<TransactionBean> transactionBeans = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionBeans);
    }

    /**
     * Returns a list of <code>nl.sprinbank.bean.TransactionBean</code> given a bankAccount.
     */
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
    }

    /**
     * Makes a new transaction.
     */
    @ApiOperation(value = "Make a transaction")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @Transactional(isolation = REPEATABLE_READ)
    public ResponseEntity<?> postTransaction(@RequestBody TransactionBean transactionBean) {
        try {
            transactionService.makeTransaction(transactionBean);
            return ResponseEntity.ok().build();
        } catch (TransactionException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
