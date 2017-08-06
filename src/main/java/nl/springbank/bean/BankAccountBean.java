package nl.springbank.bean;

import javax.persistence.*;
import java.util.Set;

/**
 * Bean representing the bank_account table. A bank account is associated with a holder.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Entity
@Table(name = "bank_account")
public class BankAccountBean {
    /*
     * Table values
     */
    /** Account identifier. */
    @Id
    @Column(name = "account_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bankAccountId;

    /** The holder associated with the account. */
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "holder_user_id", nullable = false)
    private UserBean holder;

    /** The balance on the account. */
    @Column(name = "balance", nullable = false)
    private double balance;

    /*
     * Mapped values
     */
    /** The users that have access to this bank account. */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_bank_account",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    )
    private Set<UserBean> accessUsers;

    /** The cards associated with this bank account. */
    @OneToMany(mappedBy = "card.bank_account_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CardBean> cards;

    /** The iban associated with this bank account. */
    @OneToOne(mappedBy = "iban.bank_account_id", optional = false, cascade = CascadeType.ALL)
    private IbanBean iban;

    /** The transactions with this bank account as the source. */
    @OneToMany(mappedBy = "transaction.source_account_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransactionBean> sourceTransactions;

    /** The transactions with this bank account as target. */
    @OneToMany(mappedBy = "transaction.target_account_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransactionBean> targetTransactions;

    /*
     * Bean methods
     */
    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public UserBean getHolder() {
        return holder;
    }

    public void setHolder(UserBean userBean) {
        this.holder = userBean;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<UserBean> getAccessUsers() {
        return accessUsers;
    }

    public void setAccessUsers(Set<UserBean> accessUsers) {
        this.accessUsers = accessUsers;
    }

    public Set<CardBean> getCards() {
        return cards;
    }

    public void setCards(Set<CardBean> cards) {
        this.cards = cards;
    }

    public IbanBean getIban() {
        return iban;
    }

    public void setIban(IbanBean iban) {
        this.iban = iban;
    }

    public Set<TransactionBean> getSourceTransactions() {
        return sourceTransactions;
    }

    public void setSourceTransactions(Set<TransactionBean> sourceTransactions) {
        this.sourceTransactions = sourceTransactions;
    }

    public Set<TransactionBean> getTargetTransactions() {
        return targetTransactions;
    }

    public void setTargetTransactions(Set<TransactionBean> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccountBean)) return false;

        BankAccountBean that = (BankAccountBean) o;

        if (bankAccountId != that.bankAccountId) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (holder != null ? !holder.equals(that.holder) : that.holder != null) return false;
        if (accessUsers != null ? !accessUsers.equals(that.accessUsers) : that.accessUsers != null) return false;
        if (cards != null ? !cards.equals(that.cards) : that.cards != null) return false;
        if (iban != null ? !iban.equals(that.iban) : that.iban != null) return false;
        if (sourceTransactions != null ? !sourceTransactions.equals(that.sourceTransactions) : that.sourceTransactions != null)
            return false;
        return targetTransactions != null ? targetTransactions.equals(that.targetTransactions) : that.targetTransactions == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (bankAccountId ^ (bankAccountId >>> 32));
        result = 31 * result + (holder != null ? holder.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (accessUsers != null ? accessUsers.hashCode() : 0);
        result = 31 * result + (cards != null ? cards.hashCode() : 0);
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (sourceTransactions != null ? sourceTransactions.hashCode() : 0);
        result = 31 * result + (targetTransactions != null ? targetTransactions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BankAccountBean{" +
                "bankAccountId=" + bankAccountId +
                ", holder=" + holder +
                ", balance=" + balance +
                ", accessUsers=" + accessUsers +
                ", cards=" + cards +
                ", iban=" + iban +
                ", sourceTransactions=" + sourceTransactions +
                ", targetTransactions=" + targetTransactions +
                '}';
    }
}
