package nl.springbank.bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Bean representing the transaction table. A transaction is associated with a source and a target bank account.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Entity
@Table(name = "transaction")
public class TransactionBean {
    /*
     * Table values
     */
    /** Transaction identifier. */
    @Id
    @Column(name = "transaction_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    /** The source bank account. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_account_id")
    private BankAccountBean sourceBankAccount;

    /** The target bank account. */
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "target_account_id", nullable = false)
    private BankAccountBean targetBankAccount;

    /** The target name of the transaction */
    @Column(name = "target_name")
    private String targetName;

    /** The date of the transaction */
    @Column(name = "date", nullable = false)
    private Timestamp date;

    /** The amount of the transaction */
    @Column(name = "amount", nullable = false)
    private double amount;

    /** The message of the transaction */
    @Column(name = "message")
    private String message;

    /*
     * Bean methods
     */
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public BankAccountBean getSourceBankAccount() {
        return sourceBankAccount;
    }

    public void setSourceBankAccount(BankAccountBean sourceBankAccount) {
        this.sourceBankAccount = sourceBankAccount;
    }

    public BankAccountBean getTargetBankAccount() {
        return targetBankAccount;
    }

    public void setTargetBankAccount(BankAccountBean targetBankAccount) {
        this.targetBankAccount = targetBankAccount;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionBean)) return false;

        TransactionBean that = (TransactionBean) o;

        if (transactionId != that.transactionId) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (sourceBankAccount != null ? !sourceBankAccount.equals(that.sourceBankAccount) : that.sourceBankAccount != null)
            return false;
        if (targetBankAccount != null ? !targetBankAccount.equals(that.targetBankAccount) : that.targetBankAccount != null)
            return false;
        if (targetName != null ? !targetName.equals(that.targetName) : that.targetName != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (transactionId ^ (transactionId >>> 32));
        result = 31 * result + (sourceBankAccount != null ? sourceBankAccount.hashCode() : 0);
        result = 31 * result + (targetBankAccount != null ? targetBankAccount.hashCode() : 0);
        result = 31 * result + (targetName != null ? targetName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionBean{" +
                "transactionId=" + transactionId +
                ", sourceBankAccount=" + sourceBankAccount +
                ", targetBankAccount=" + targetBankAccount +
                ", targetName='" + targetName + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                '}';
    }
}
