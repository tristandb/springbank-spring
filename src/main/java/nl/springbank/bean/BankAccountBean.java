package nl.springbank.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@Entity
@Table(name = "bank_account", uniqueConstraints = {@UniqueConstraint(columnNames = "account_id")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountBean {
     /*
        Private methods
     */

    // Autogenerated id (unique for each bank_account in the db)
    @Id
    @Column(name = "account_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bankAccountId;

    // The balance on the account
    @Column(name = "balance")
    private double balance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bankAccountBean")
    @JsonManagedReference
    @JsonProperty("iban")
    private IbanBean ibanBean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holder_user_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    @JsonProperty("holder")
    private UserBean user;

    @Column(name = "holder_user_id")
    @JsonProperty("userId")
    private long userId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, updatable = false, insertable = false)
    @JoinTable(name = "user_bank_account", joinColumns = @JoinColumn(name = "bank_account_id",
            referencedColumnName = "account_id"), inverseJoinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "user_id"))
    @JsonBackReference
    private Set<UserBean> users;

    /*
        Public methods
     */

    public BankAccountBean() {
    }

    public BankAccountBean(double balance) {
        this.balance = balance;
    }

    public BankAccountBean(double balance, IbanBean ibanBean) {
        this.balance = balance;
        this.ibanBean = ibanBean;
    }

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @OneToOne(cascade = CascadeType.ALL)
    public IbanBean getIbanBean() {
        return ibanBean;
    }

    public void setIbanBean(IbanBean ibanBean) {
        this.ibanBean = ibanBean;
    }

    @Override
    public String toString() {
        return "BankAccountBean{" +
                "bankAccountId=" + bankAccountId +
                ", balance=" + balance +
                ", ibanBean=" + ibanBean +
                ", user=" + user +
                '}';
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean userBean) {
        this.user = userBean;
    }

    public Set<UserBean> getUsers() {
        return users;
    }

    public void setUsers(Set<UserBean> users) {
        this.users  = users;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
