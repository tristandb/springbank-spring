package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import nl.springbank.bean.UserBean;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A class to get the interactions from the MySQL database using the UserDao class.
 *
 * @author Tristan de Boer).
 */
@Api(value = "user", description = "Manage users.")
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Autowire <code>UserService</code>.
     */
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a list of <code>nl.springbank.bean.UserBean</code>.
     */
    @ApiOperation(value = "Get Users")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() throws Exception {
        Iterable<UserBean> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }

    /**
     * Returns a <code>nl.springbank.bean.UserBean</code> having provided an userId.
     *
     * @param userId The userId
     * @return
     */
    @ApiOperation(value = "Get User by userId",
            notes = "Gets the user by id")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String userId) throws Exception {
        long userIdLong = Long.parseLong(userId);
        UserBean user = userService.getUser(userIdLong);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Creates a new entry for <code>nl.springbank.bean.UserBean</code>.
     */
    @ApiOperation(value = "Create a new User")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody UserBean userBean) {
        try {
            UserBean userBeanResult = userService.saveUser(userBean);
            return ResponseEntity.ok(userBeanResult);
        } catch (Exception e) {
            // TODO: Catch duplicate email.
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deletes a entry of <code>nl.springbank.bean.UserBean</code> given a userId.
     */
    @ApiOperation(value = "Delete User")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String userId) {
        userService.deleteUser(Long.parseLong(userId));
        return ResponseEntity.ok().build();

    }

    /**
     * Identifies a user, given an email.
     */
    @ApiOperation(value = "Identify a user")
    @RequestMapping(value = "/identify", method = RequestMethod.POST)
    ResponseEntity<?> identify(@RequestBody String email) {
        UserBean user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user.getId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Authenticates a user, given an IBAN.
     */
    @ApiOperation(value = "Sends the user a key if the user enters correct identification for his bank account.")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> authenticate(@RequestBody String iban) {
        try {
            String key = userService.authenticateUser(iban);
            return ResponseEntity.ok(key);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
