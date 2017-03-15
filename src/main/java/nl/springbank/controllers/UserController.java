package nl.springbank.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.springbank.bean.UserBean;
import nl.springbank.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Autowire <code>UserDao</code>.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Returns a list of <code>nl.springbank.bean.UserBean</code>.
     */
    @ApiOperation(value = "Get Users")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(){
        try {
            Iterable<UserBean> users = userDao.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Returns a <code>nl.springbank.bean.UserBean</code> having provided an userId.
     * @param userId The userId
     * @return
     */
    @ApiOperation(value = "Get User by userId",
            notes = "Gets the user by id")
    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String userId){
        try {
            long userIdLong = Long.parseLong(userId);
            UserBean user = userDao.findOne(userIdLong);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Creates a new entry for <code>nl.springbank.bean.UserBean</code>.
     */
    @ApiOperation(value = "Create a new User")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody UserBean userBean) {
            try {
                userDao.save(userBean);
                return ResponseEntity.ok().build();
            } catch (Exception e){
                // TODO: Catch duplicate email.
                return ResponseEntity.badRequest().build();
            }
    }

    /**
     * Deletes a entry of <code>nl.springbank.bean.UserBean</code> given a userId.
     */
    @ApiOperation(value = "Delete User")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String userId){
        try {
            this.userDao.delete(Long.parseLong(userId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
