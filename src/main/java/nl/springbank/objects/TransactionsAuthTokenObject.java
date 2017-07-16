package nl.springbank.objects;

/**
 * @author Sven Konings.
 */
public class TransactionsAuthTokenObject extends IbanAuthTokenObject {
    private int nrOfTransactions;

    public TransactionsAuthTokenObject(String authToken, String iBAN, int nrOfTransactions) {
        super(authToken, iBAN);
        this.nrOfTransactions = nrOfTransactions;
    }

    public int getNrOfTransactions() {
        return nrOfTransactions;
    }

    public void setNrOfTransactions(int nrOfTransactions) {
        this.nrOfTransactions = nrOfTransactions;
    }
}
