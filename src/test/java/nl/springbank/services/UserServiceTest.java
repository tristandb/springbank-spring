package nl.springbank.services;

import com.google.common.collect.Iterators;
import javassist.NotFoundException;
import junit.framework.TestCase;
import nl.springbank.bean.UserBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.sql.Date;

/**
 * Tests UserService
 *
 * Service is tested on profile 'test', which means 'application-test.properties' is used as configuration.
 * A test is annotated Transactional if changes are made to the database. This ensures a rollback.
 *
 * @author Tristan de Boer.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(nl.springbank.config.TestConfiguration.class)
@ActiveProfiles("test")
public class UserServiceTest extends TestCase {

    @Autowired
    private UserService userService;

    /**
     * Tests if a single UserBean is returned.
     * @throws Exception
     */
    @Test
    public void getSingleExistingUser() throws Exception {
        long userId = 3;
        UserBean userBean = userService.getUser(userId);
        Assert.assertEquals(userId, userBean.getId());
    }

    /**
     * Tests if a single UserBean is returned when an invalid userId is given.
     * @throws Exception
     */
    @Test
    public void getSingleNonExistingUser() throws Exception {
        long userId = 42;
        UserBean userBean = userService.getUser(userId);
        Assert.assertEquals(null, userBean);
    }

    /**
     * Gets a list of all users and checks if this list has the correct length.
     * @throws Exception
     */
    @Test
    public void getAllUsers() throws Exception {
        // Get a list of all users
        Iterable<UserBean> userBeanIterable = userService.getAllUsers();

        // Check if the count of all users equals 4
        Assert.assertEquals(4, Iterators.size(userBeanIterable.iterator()));
    }

    /**
     * Delete a single user.
     */
    @Test
    @Transactional
    public void deleteSingleUser() throws Exception {
        int userId = 1;
        // Check if user with userId userId is not null
        Assert.assertNotEquals(null, userService.getUser(userId));
        // Delete user with userId userId
        userService.deleteUser(userId);
        // Check if user with userId userId is null
        Assert.assertEquals(null, userService.getUser(userId));

        int userId2 = 4;
        // Check if user with userId userId2 is not null
        Assert.assertNotEquals(null, userService.getUser(userId2));
        // Delete user with userId userId2
        userService.deleteUser(userId2);
        // Check if user with userId userId2 is null
        Assert.assertEquals(null, userService.getUser(userId2));
    }

    /**
     * Retrieves the list of all users and deletes them.
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void deleteAllUsers() throws Exception {
        // Get a list of all users
        Iterable<UserBean> userIterable = userService.getAllUsers();

        // Delete all users
        for (UserBean userBean : userIterable) {
            userService.deleteUser(userBean.getId());
        }

        // Check that all users are deleted
        Assert.assertEquals(0, Iterators.size(userService.getAllUsers().iterator()));
    }

    /**
     * Test if adding a UserBean works.
     * @throws Exception
     */
    @Test
    @Transactional
    public void testUserPost() throws Exception {
        // Define all variables
        String name = "John";
        String surname = "Doe";
        String initials = "J.";
        String bsn = "098765431";
        String streetAddress = "Wallstreet 1";
        String telephoneNumber = "0612345678";
        String email = "john@doe.com";
        Date dateOfBirth = new Date((new java.util.Date()).getTime());

        // Create a UserBean
        UserBean userBean = new UserBean();
        userBean.setName(name);
        userBean.setSurname(surname);
        userBean.setInitials(initials);
        userBean.setBsn(bsn);
        userBean.setStreetAddress(streetAddress);
        userBean.setTelephoneNumber(telephoneNumber);
        userBean.setUsername(email);
        userBean.setDateOfBirth(dateOfBirth);

        // Save the UserBean
        long userId = userService.saveUser(userBean).getId();

        // Check if saved UserBean compares to the original UserBean
        Assert.assertNotEquals(null, userId);
        UserBean savedUserBean = userService.getUser(userId);
        Assert.assertEquals(userBean.getName(), savedUserBean.getName());
        Assert.assertEquals(userBean.getSurname(), savedUserBean.getSurname());
        Assert.assertEquals(userBean.getInitials(), savedUserBean.getInitials());
        Assert.assertEquals(userBean.getBsn(), savedUserBean.getBsn());
        Assert.assertEquals(userBean.getStreetAddress(), savedUserBean.getStreetAddress());
        Assert.assertEquals(userBean.getTelephoneNumber(), savedUserBean.getTelephoneNumber());
        Assert.assertEquals(userBean.getUsername(), savedUserBean.getUsername());
        Assert.assertEquals(userBean.getDateOfBirth().toLocalDate(), savedUserBean.getDateOfBirth().toLocalDate());
    }

    /**
     * Test if adding a duplicate email fails
     */
    @Test
    @Transactional
    public void testDuplicateEmail() throws Exception {
        // Define all variables
        String name = "John";
        String surname = "Doe";
        String initials = "J.";
        String bsn = "098765431";
        String streetAddress = "Wallstreet 1";
        String telephoneNumber = "0612345678";
        String email = "ban@aan.nl";
        Date dateOfBirth = new Date((new java.util.Date()).getTime());

        // Create a UserBean
        UserBean userBean = new UserBean();
        userBean.setName(name);
        userBean.setSurname(surname);
        userBean.setInitials(initials);
        userBean.setBsn(bsn);
        userBean.setStreetAddress(streetAddress);
        userBean.setTelephoneNumber(telephoneNumber);
        userBean.setUsername(email);
        userBean.setDateOfBirth(dateOfBirth);

        // Save the UserBean
        try {
            userService.saveUser(userBean);
            fail();
        } catch (DataIntegrityViolationException e){
            Assert.assertNotNull(e);
        }
    }

    /**
     * Test authenticateUser.
     */
    @Test
    public void testAuthenticateUser() throws Exception {
        String iban = "NL17SPRI0466994144";
        Assert.assertEquals("kaas", userService.authenticateUser(iban));
    }

    /**
     * Test authenticateUser with an invalid IBAN.
     */
    @Test
    public void testAuthenticateInvalidUser() throws Exception {
        String iban = "NL17SPRI0466994145";
        try {
            userService.authenticateUser(iban);
            fail();
        } catch (NotFoundException ignored){

        }
    }
}
