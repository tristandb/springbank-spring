package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.TransactionBean;
import nl.springbank.dao.TransactionDao;
import nl.springbank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@Api(value = "transaction", description = "Manage Transaction")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    /**
     * Autowire <code>nl.springbank.services.TransactionService</code>
     */
    @Autowired
    private TransactionService transactionService;



    /**
     * Returns a list of <code>nl.springbank.bean.TransactionBean</code>
     */
    @ApiOperation(value = "Return Transactions")
    @ResponseBody
    @RequestMapping
    public ResponseEntity<?> getTransactions() {
        try {
            Iterable<TransactionBean> transactionBeans = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactionBeans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Returns a list of outgoing transactions <code>nl.springbank.bean.TransactionBean</code>.
     */
    @ApiOperation(value = "Return Transactions given IBAN")
    @ResponseBody
    @RequestMapping("/iban/{iban}")
    public ResponseEntity<?> getTransactionByIban(@PathVariable String iban) {
        try {
            Iterable<TransactionBean> transactionBeans = transactionService.getTransactionsByIban(iban);
            return ResponseEntity.ok(transactionBeans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
