package nl.springbank.exceptions;

/**
 * One or more parameter has an invalid value.
 *
 * @author Tristan de Boer.
 */
public class InvalidParamValueError extends Throwable {
    public InvalidParamValueError(String message) {
        super(message);
    }
}
