package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.TransactionBean;
import nl.springbank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Autowire <code>nl.springbank.services.TransactionService</code>
     */
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
        try {
            Iterable<TransactionBean> transactionBeans = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactionBeans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Returns a list of <code>nl.sprinbank.bean.TransactionBean</code> given a bankAccount.
     */
    @ApiOperation(value = "Return Transactions given BankAccountId")
    @ResponseBody
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionsByAccountId(@PathVariable String accountId) {
        try {
            Iterable<TransactionBean> transactionBeans = transactionService.getTransactionsById(Long.parseLong(accountId));
            return ResponseEntity.ok(transactionBeans);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Returns a lis of outgoing transactions <code>nl.springbank.bean.TransactionBean</code>.
     */
    @ApiOperation(value = "Return Transactions given IBAN")
    @ResponseBody
    @RequestMapping(value = "/iban/{iban}", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionByIban(@PathVariable String iban) {
        try {
            // TODO: Get incoming transactions
            Iterable<TransactionBean> transactionBeans = transactionService.getTransactionsByIban(iban);
            return ResponseEntity.ok(transactionBeans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Makes a new transaction
     */
    @ApiOperation(value = "Do a transaction")
    @ResponseBody
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<?> postTransaction(@RequestBody TransactionBean transactionBean) {
        try {
            boolean transactionResult = transactionService.doTransaction(transactionBean);
            return ResponseEntity.ok(transactionResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
