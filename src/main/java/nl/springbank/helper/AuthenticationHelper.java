package nl.springbank.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import nl.springbank.exceptions.NotAuthorizedError;

import java.util.Date;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class AuthenticationHelper {
    public static final String PRIVATE_KEY = "krCmGA09Th9SUdkSUu7nODAqP0US7J2F";

    /**
     * Get the authentication token belonging to the given user id.
     *
     * @param userId the given user id
     * @return the authentication token
     */
    public static String getAuthToken(long userId) {
        return Jwts.builder().setSubject(String.valueOf(userId)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, AuthenticationHelper.PRIVATE_KEY).compact();
    }

    /**
     * Get the user id belonging to the given authentication token.
     *
     * @param authToken the given authentication token
     * @return the user id
     * @throws NotAuthorizedError if the signature of the authentication token is incorrect
     */
    public static long getUserId(String authToken) throws NotAuthorizedError {
        try {
            Claims claims = Jwts.parser().setSigningKey(PRIVATE_KEY).parseClaimsJws(authToken).getBody();
            return Long.parseLong(claims.getSubject());
        } catch (SignatureException e) {
            throw new NotAuthorizedError("Signature is incorrect");
        }
    }
}
