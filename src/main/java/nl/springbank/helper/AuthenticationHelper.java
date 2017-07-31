package nl.springbank.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import nl.springbank.exceptions.NotAuthorizedError;

/**
 * Description
 *
 * @author Tristan de Boer.
 */
public class AuthenticationHelper {
    public static String PRIVATE_KEY = "krCmGA09Th9SUdkSUu7nODAqP0US7J2F";

    public static long getUserId(String authToken) throws NotAuthorizedError {
        try {
            final Claims claims = Jwts.parser().setSigningKey(PRIVATE_KEY).parseClaimsJws(authToken).getBody();
            return Long.valueOf(claims.getSubject());
        } catch (SignatureException e) {
            throw new NotAuthorizedError("Signature is not correct");
        }
    }
}
