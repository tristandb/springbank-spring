package nl.springbank.controllers;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Authentication controller that enables a user to get access and revoke access.
 *
 * @author Tristan de Boer.
 */
@Service
@JsonRpcService("/api")
@AutoJsonRpcServiceImpl
public class AuthenticationController {

    /**
     * Attempts to log in and returns an authentication token.
     * @param username The username of the customer.
     * @param password The password of the customer.
     */
    public String getAuthToken (@JsonRpcParam(value = "username") String username, @JsonRpcParam(value = "password") String password) {
        // Spring Auth Token library.
        return Jwts.builder().setSubject(username).claim("roles", username).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "test").compact();
    }
}
