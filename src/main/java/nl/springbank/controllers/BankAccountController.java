package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.UserBean;
import nl.springbank.dao.BankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Autowire <code>BankAccountDao</code>
     */
    @Autowired
    private BankAccountDao bankAccountDao;

    /**
     * Returns a list of <code>nl.springbank.bean.BankAccountBean</code>.
     * @return
     */
    @ApiOperation(value = "Return BankAccounts")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getBankAccounts(){
        try {
            Iterable<BankAccountBean> bankAccountBean = bankAccountDao.findAll();
            return ResponseEntity.ok(bankAccountBean);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Returns a <code>nl.springbank.bean.BankAccountBean</code> having provided an bankAccountId.
     * @param bankAccountId The bankAccountId
     * @return
     */
    @ApiOperation(value = "Return BankAccount by bankAccountId")
    @ResponseBody
    @RequestMapping(value = "/{bankAccountId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBankAccount(@PathVariable String bankAccountId){
        try {
            BankAccountBean bankAccountBean = bankAccountDao.findOne(Long.parseLong(bankAccountId));
            return ResponseEntity.ok(bankAccountBean);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Creates a new entry for <code>nl.springbank.bean.BankAccountBean</code>.
     */
    @ApiOperation(value = "Create a new BankAccount")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody BankAccountBean bankAccountBean) {
        try {
            BankAccountBean savedBankAccount = bankAccountDao.save(bankAccountBean);
            return ResponseEntity.ok(savedBankAccount.getBankAccountId());
        } catch (Exception e){
            // TODO: Catch duplicate IBAN.
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes a entry of <code>nl.springbank.bean.BankAccountBean</code> given a bankAccountId.
     */
    @ApiOperation(value = "Delete BankAccount")
    @RequestMapping(value = "/{bankAccountId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String bankAccountId){
        try {
            this.bankAccountDao.delete(Long.parseLong(bankAccountId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Returns a <code>nl.springbank.bean.BankAccountBean</code> having provided an IBAN.
     * @param iban The IBAN
     * @return
     */
    @ApiOperation(value = "Return BankAccount")
    @ResponseBody
    @RequestMapping(value = "/iban/{iban}", method = RequestMethod.GET)
    public ResponseEntity<?> getBankAccountByIban(@PathVariable String iban){
        try {
            BankAccountBean bankAccountBean = bankAccountDao.findByIban(iban);
            return ResponseEntity.ok(bankAccountBean);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
