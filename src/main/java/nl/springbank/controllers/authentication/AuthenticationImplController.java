package nl.springbank.controllers.authentication;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.bean.UserBean;
import nl.springbank.exceptions.AuthenticationError;
import nl.springbank.objects.AuthenticationObject;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Service
@AutoJsonRpcServiceImpl
public class AuthenticationImplController implements AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationImplController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthenticationObject getAuthToken(String username, String password) throws AuthenticationError {
        UserBean user = userService.authUser(username, password);
        return new AuthenticationObject(user);
    }
}
