package nl.springbank.controllers;

import nl.springbank.bean.UserBean;
import nl.springbank.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to get the interactions from the MySQL database using the UserDao class.
 *
 * @author Tristan de Boer).
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Autowire userDao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Returns a <code>UserBean</code> having provided an userId. Returns null if no user has been found.
     * @param userId The userId
     * @return
     */
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
