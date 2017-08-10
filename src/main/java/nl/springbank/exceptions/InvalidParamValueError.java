package nl.springbank.exceptions;

/**
 * One or more parameter has an invalid value.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class InvalidParamValueError extends Exception {
    public InvalidParamValueError() {
    }

    public InvalidParamValueError(String message) {
        super(message);
    }

    public InvalidParamValueError(Throwable cause) {
        super(cause);
    }
}
