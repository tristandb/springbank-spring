package nl.springbank.exceptions;

/**
 * An invalid PINcard, -code or -combination was used.
 *
 * @author Sven Konings
 */
public class InvalidPINError extends Exception {
    public InvalidPINError() {
    }

    public InvalidPINError(String message) {
        super(message);
    }

    public InvalidPINError(Throwable cause) {
        super(cause);
    }
}
