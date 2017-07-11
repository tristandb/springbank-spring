package nl.springbank.exceptions;

/**
 * The user is not authorized to take this action.
 *
 * @author Tristan de Boer.
 */
public class NotAuthorizedError extends Throwable {

    public NotAuthorizedError(String s) {
        super(s);
    }
}
