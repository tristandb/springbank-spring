package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class BalanceObject {
    private double balance;

    public BalanceObject(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
