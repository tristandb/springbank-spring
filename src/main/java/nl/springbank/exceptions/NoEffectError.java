package nl.springbank.exceptions;

/**
 * A method has no effect.
 *
 * @author Sven Konings.
 */
public class NoEffectError extends Throwable {
    public NoEffectError(String message){
        super(message);
    }
}
