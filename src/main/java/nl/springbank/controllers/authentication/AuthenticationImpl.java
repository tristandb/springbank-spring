package nl.springbank.controllers.authentication;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.objects.AuthenticationObject;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@Service
@AutoJsonRpcServiceImpl
public class AuthenticationImpl implements Authentication {

    @Override
    public AuthenticationObject getAuthToken(@JsonRpcParam(value = "username") String username, @JsonRpcParam(value = "password") String password) {
        // TODO: Check password.
        String authToken = Jwts.builder().setSubject(username).claim("roles", username).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "test").compact();
        return new AuthenticationObject(authToken);
    }
}
