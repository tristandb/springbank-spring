package nl.springbank.objects;

/**
 * @author Sven Konings
 */
public class TransactionsOverviewObject extends IbanAuthTokenObject {
    private int nrOfTransactions;

    public TransactionsOverviewObject(int userId, String iBAN, int nrOfTransactions) {
        super(userId, iBAN);
        this.nrOfTransactions = nrOfTransactions;
    }

    public int getNrOfTransactions() {
        return nrOfTransactions;
    }

    public void setNrOfTransactions(int nrOfTransactions) {
        this.nrOfTransactions = nrOfTransactions;
    }
}
