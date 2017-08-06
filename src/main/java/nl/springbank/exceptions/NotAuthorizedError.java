package nl.springbank.exceptions;

/**
 * The user is not authorized to take this action.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class NotAuthorizedError extends Exception {
    public NotAuthorizedError() {
    }

    public NotAuthorizedError(String message) {
        super(message);
    }

    public NotAuthorizedError(Throwable cause) {
        super(cause);
    }
}
