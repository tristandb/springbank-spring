package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.UserBankAccountBean;
import nl.springbank.bean.UserIbanBean;
import nl.springbank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * A class to get the interactions from the MySQL database using the BankAccountDao class.
 *
 * @author Tristan de Boer.
 */
@Api(value = "bankaccount", description = "Manage BankAccount.")
@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
    /**
     * Autowire <code>BankAccountService</code>
     */
    @Autowired
    private BankAccountService bankAccountService;

    /**
     * Returns a list of <code>nl.springbank.bean.BankAccountBean</code>.
     *
     * @return
     */
    @ApiOperation(value = "Return BankAccounts")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getBankAccounts() throws Exception {
        Iterable<BankAccountBean> bankAccountBean = bankAccountService.getBankAccounts();
        return ResponseEntity.ok(bankAccountBean);
    }

    /**
     * Returns a <code>nl.springbank.bean.BankAccountBean</code> having provided an bankAccountId.
     *
     * @param bankAccountId The bankAccountId
     * @return
     */
    @ApiOperation(value = "Return BankAccount by bankAccountId")
    @ResponseBody
    @RequestMapping(value = "/{bankAccountId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBankAccount(@PathVariable String bankAccountId) throws Exception {
        BankAccountBean bankAccountBean = bankAccountService.getBankAccount(Long.parseLong(bankAccountId));
        if (bankAccountBean != null) {
            return ResponseEntity.ok(bankAccountBean);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Creates a new entry for <code>nl.springbank.bean.BankAccountBean</code>.
     */
    @ApiOperation(value = "Create a new BankAccount")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody BankAccountBean bankAccountBean) {
        try {
            BankAccountBean savedBankAccount = bankAccountService.saveBankAccount(bankAccountBean);
            return ResponseEntity.ok(savedBankAccount.getBankAccountId());
        } catch (Exception e) {
            // TODO: Catch duplicate IBAN.
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes a entry of <code>nl.springbank.bean.BankAccountBean</code> given a bankAccountId.
     */
    @ApiOperation(value = "Delete BankAccount")
    @RequestMapping(value = "/{bankAccountId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String bankAccountId) {
        try {
            bankAccountService.deleteBankAccount(Long.parseLong(bankAccountId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Connects a user to a bankaccount.
     *
     * @return
     */
    @ApiOperation(value = "Connects a user to a bankaccount.")
    @RequestMapping(value = "users/iban", method = RequestMethod.POST)
    ResponseEntity<?> connectUserByIban(@RequestBody UserIbanBean userIbanBean) {
        try {
            UserBankAccountBean userBankAccountBean = bankAccountService.connectUserByIban(userIbanBean);
            return ResponseEntity.ok(userBankAccountBean);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Connects a user to a bankaccount.")
    @RequestMapping(value = "users", method = RequestMethod.POST)
    ResponseEntity<?> connectUser(@RequestBody UserBankAccountBean userBankAccountBean) {
        try {
            UserBankAccountBean savedUserBankAcountBean = bankAccountService.connectUser(userBankAccountBean);
            return ResponseEntity.ok(savedUserBankAcountBean);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    /* @ApiOperation(value = "Return BankAccount")
    @ResponseBody
    @RequestMapping(value = "/iban/{iban}", method = RequestMethod.GET)
    public ResponseEntity<?> getBankAccountByIban(@PathVariable String iban) {
        try {
            BankAccountBean bankAccountBean = bankAccountDao.findByIban(iban);
            return ResponseEntity.ok(bankAccountBean);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }*/
}
