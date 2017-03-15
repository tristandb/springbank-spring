package nl.springbank.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * TransactionBean.
 *
 * @author Tristan de Boer).
 */
public class TransactionBean {
    /*
        Private methods
     */
    // Transaction identifier
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    // source bank account iban
    @Column(name = "source_bank_account_iban")
    private String sourceBankAccountIban;

    // target bank account iban
    @Column(name = "target_bank_account_iban")
    private String targetBankAccountIban;

    // source bank account
    @Column(name = "source_bank_account")
    private int sourceBankAccount;

    // target bank account
    @Column(name = "target_bank_account")
    private int targetBankAccount;

    // Amount transferred
    @NotNull
    private double amount;

    // Message as specified by the transfer
    private String message;

    /*
        Public methods
     */

    public TransactionBean() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getSourceBankAccountIban() {
        return sourceBankAccountIban;
    }

    public void setSourceBankAccountIban(String sourceBankAccountIban) {
        this.sourceBankAccountIban = sourceBankAccountIban;
    }

    public String getTargetBankAccountIban() {
        return targetBankAccountIban;
    }

    public void setTargetBankAccountIban(String targetBankAccountIban) {
        this.targetBankAccountIban = targetBankAccountIban;
    }

    public int getSourceBankAccount() {
        return sourceBankAccount;
    }

    public void setSourceBankAccount(int sourceBankAccount) {
        this.sourceBankAccount = sourceBankAccount;
    }

    public int getTargetBankAccount() {
        return targetBankAccount;
    }

    public void setTargetBankAccount(int targetBankAccount) {
        this.targetBankAccount = targetBankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransactionBean{" +
                "transactionId=" + transactionId +
                ", sourceBankAccountIban='" + sourceBankAccountIban + '\'' +
                ", targetBankAccountIban='" + targetBankAccountIban + '\'' +
                ", sourceBankAccount=" + sourceBankAccount +
                ", targetBankAccount=" + targetBankAccount +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                '}';
    }
}
