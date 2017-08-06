package nl.springbank.exceptions;

/**
 * Thrown when the transaction has not succeeded.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class TransactionException extends Exception {
    public TransactionException() {
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
}
