package nl.springbank.objects;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.springbank.helper.AuthenticationHelper;

import java.util.Date;

/**
 * @author Tristan de Boer.
 */
public class AuthTokenObject {
    private String authToken;

    public AuthTokenObject(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static AuthTokenObject create(long subject) {
        return new AuthTokenObject(Jwts.builder().setSubject(String.valueOf(subject)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthenticationHelper.PRIVATE_KEY).compact());
    }
}
