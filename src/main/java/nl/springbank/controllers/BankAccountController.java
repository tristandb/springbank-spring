package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.UserBean;
import nl.springbank.dao.BankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to get the interactions from the MySQL database using the BankAccountDao class.
 *
 * @author Tristan de Boer.
 */
@Api(value = "bankaccount", description = "Used to manage bank accounts.")
@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
    /**
     * Autowire <code>BankAccountDao</code>
     */
    @Autowired
    private BankAccountDao bankAccountDao;

    @ApiOperation(value = "Get BankAccount",
            notes = "Returns a BankAccount given a bankAccountId")
    @ResponseBody
    @RequestMapping("")
    public BankAccountBean getUser(Long bankAccountId){
        try {
            return bankAccountDao.findOne(bankAccountId);
        } catch (Exception e){
            return null;
        }
    }
}
