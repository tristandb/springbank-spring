package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.springbank.bean.UserBean;
import nl.springbank.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to get the interactions from the MySQL database using the UserDao class.
 *
 * @author Tristan de Boer).
 */
@Api(value = "user", description = "Used to manage users.")
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Autowire <code>UserDao</code>.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Returns a <code>nl.springbank.bean.UserBean</code> having provided an userId. Returns null if no user has been found.
     * @param userId The userId
     * @return
     */
    @ApiOperation(value = "Used to get user",
            notes = "Gets the user by id")
    @ResponseBody
    @RequestMapping("")
    public UserBean getUser(Long userId){
        try {
            return userDao.findOne(userId);
        } catch (Exception e){
            return null;
        }
    }
}
