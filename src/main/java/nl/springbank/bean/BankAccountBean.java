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
    @ManyToOne(optional = false)
    @JoinColumn(name = "holder_user_id", nullable = false)
    private UserBean holder;

    /** The balance on the account. */
    @Column(name = "balance", nullable = false)
    private double balance;

    /*
     * Mapped values
     */
    /** The users that have access to this bank account. */
    @ManyToMany
    @JoinTable(
            name = "user_bank_account",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    )
    private Set<UserBean> accessors;

    /** The cards associated with this bank account. */
    @OneToMany(mappedBy = "card.bank_account_id")
    private Set<CardBean> cards;

    /** The iban associated with this bank account. */
    @OneToOne(mappedBy = "iban.bank_account_id", optional = false)
    private IbanBean iban;

    /** The transactions with this bank account as the source. */
    @OneToMany(mappedBy = "transaction.source_account_id")
    private Set<TransactionBean> sourceTransactions;

    /** The transactions with this bank account as target. */
    @OneToMany(mappedBy = "transaction.target_account_id")
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

    public Set<UserBean> getAccessors() {
        return accessors;
    }

    public void setAccessors(Set<UserBean> accessors) {
        this.accessors = accessors;
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
    public String toString() {
        return "BankAccountBean{" +
                "bankAccountId=" + bankAccountId +
                ", holder=" + holder +
                ", balance=" + balance +
                ", accessors=" + accessors +
                ", cards=" + cards +
                ", iban=" + iban +
                ", sourceTransactions=" + sourceTransactions +
                ", targetTransactions=" + targetTransactions +
                '}';
    }
}
