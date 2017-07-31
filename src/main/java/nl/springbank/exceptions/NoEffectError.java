package nl.springbank.exceptions;

/**
 * The action has no effect.
 *
 * @author Sven Konings.
 */
public class NoEffectError extends Exception {
    public NoEffectError(String message) {
        super(message);
    }
}
