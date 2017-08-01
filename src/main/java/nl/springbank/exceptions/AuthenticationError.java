package nl.springbank.exceptions;

/**
 * The user could not be authenticated. Invalid username, password or combination.
 *
 * @author Tristan de Boer.
 */
public class AuthenticationError extends Exception {
    public AuthenticationError() {
    }

    public AuthenticationError(String message) {
        super(message);
    }
}
