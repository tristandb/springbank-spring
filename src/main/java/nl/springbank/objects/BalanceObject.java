package nl.springbank.objects;

import nl.springbank.bean.BankAccountBean;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class BalanceObject {
    private double balance;

    public BalanceObject(BankAccountBean bankAccountBean) {
        this.balance = bankAccountBean.getBalance();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
