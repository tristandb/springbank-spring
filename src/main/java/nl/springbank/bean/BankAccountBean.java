package nl.springbank.bean;

import javax.persistence.*;
import java.util.Set;
import java.util.SortedSet;

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
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bankAccountId;

    /** The holder associated with the account. */
    @ManyToOne
    @JoinColumn(name = "holder_user_id")
    private UserBean holder;

    /** The balance on the account. */
    @Column(name = "balance")
    private double balance;

    /*
     * Mapped values
     */
    /** The iban associated with this bank account. */
    @OneToOne(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private IbanBean iban;

    /** The users that have access to this bank account. */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_bank_account",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private Set<UserBean> accessUsers;

    /** The cards associated with this bank account. */
    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CardBean> cards;

    /** The transactions with this bank account as the source. */
    @OneToMany(mappedBy = "sourceBankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private SortedSet<TransactionBean> sourceTransactions;

    /** The transactions with this bank account as target. */
    @OneToMany(mappedBy = "targetBankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private SortedSet<TransactionBean> targetTransactions;

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

    public IbanBean getIban() {
        return iban;
    }

    public void setIban(IbanBean iban) {
        this.iban = iban;
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

    public SortedSet<TransactionBean> getSourceTransactions() {
        return sourceTransactions;
    }

    public void setSourceTransactions(SortedSet<TransactionBean> sourceTransactions) {
        this.sourceTransactions = sourceTransactions;
    }

    public SortedSet<TransactionBean> getTargetTransactions() {
        return targetTransactions;
    }

    public void setTargetTransactions(SortedSet<TransactionBean> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }

    @Override
    public String toString() {
        return "BankAccountBean{" +
                "bankAccountId=" + bankAccountId +
                ", holder=" + holder +
                ", balance=" + balance +
                '}';
    }
}
