package nl.springbank.controllers.authentication;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.exceptions.AuthenticationError;
import nl.springbank.objects.AuthenticationObject;
import nl.springbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Tristan de Boer).
 */
@Service
@AutoJsonRpcServiceImpl
public class AuthenticationImpl implements Authentication {

    /**
     * Autowire <code>UserService</code>.
     */
    private final UserService userService;

    @Autowired
    public AuthenticationImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Attempts to log in and returns an authentication token.
     * @param username The username of the customer
     * @param password The password of the customer
     * @throws AuthenticationError The user could not be authenticated. Invalid username, password or combination.
     * @return AuthenticationObject
     */
    @Override
    public AuthenticationObject getAuthToken(@JsonRpcParam(value = "username") String username, @JsonRpcParam(value = "password") String password) throws AuthenticationError {
        if (userService.isCorrectPassword(username, password)) {
            String authToken = Jwts.builder().setSubject(username).claim("roles", username).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS512, "test").compact();
            return new AuthenticationObject(authToken);
        } else {
            throw new AuthenticationError();
        }
    }
}
