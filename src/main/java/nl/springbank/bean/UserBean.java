package nl.springbank.bean;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Bean representing the bank_account table.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Entity
@Table(name = "user")
public class UserBean {
    /*
     * Table values
     */

    /** User identifier. */
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    /** The name of the user. */
    @Column(name = "name", nullable = false)
    private String name;

    /** The surname of the user. */
    @Column(name = "surname", nullable = false)
    private String surname;

    /** The initials of the user. */
    @Column(name = "initials", nullable = false)
    private String initials;

    /** The date of birth of the user. */
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    /** The BSN of the user. */
    @Column(name = "bsn", unique = true, nullable = false)
    private String bsn;

    /** The street address of the user. */
    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    /** The telephone number of the user. */
    @Column(name = "telephone_number", nullable = false)
    private String telephoneNumber;

    /** The username of the user. */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /** The password of the user. */
    @Column(name = "password", nullable = false)
    private String password;

    /** The email of the user. */
    @Column(name = "email", nullable = false)
    private String email;

    /*
     * Mapped values
     */
    /** The accounts this user is the holder of. */
    @OneToMany(mappedBy = "holder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BankAccountBean> holderAccounts;

    /** The accounts this user has access to. */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_bank_account",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    )
    private Set<BankAccountBean> accessAccounts;

    /** The cards of the user. */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CardBean> cards;

    /*
     * Bean methods
     */
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BankAccountBean> getHolderAccounts() {
        return holderAccounts;
    }

    public void setHolderAccounts(Set<BankAccountBean> holderAccounts) {
        this.holderAccounts = holderAccounts;
    }

    public Set<BankAccountBean> getAccessAccounts() {
        return accessAccounts;
    }

    public void setAccessAccounts(Set<BankAccountBean> accessAccounts) {
        this.accessAccounts = accessAccounts;
    }

    public Set<CardBean> getCards() {
        return cards;
    }

    public void setCards(Set<CardBean> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBean)) return false;

        UserBean userBean = (UserBean) o;

        if (userId != userBean.userId) return false;
        if (name != null ? !name.equals(userBean.name) : userBean.name != null) return false;
        if (surname != null ? !surname.equals(userBean.surname) : userBean.surname != null) return false;
        if (initials != null ? !initials.equals(userBean.initials) : userBean.initials != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(userBean.dateOfBirth) : userBean.dateOfBirth != null)
            return false;
        if (bsn != null ? !bsn.equals(userBean.bsn) : userBean.bsn != null) return false;
        if (streetAddress != null ? !streetAddress.equals(userBean.streetAddress) : userBean.streetAddress != null)
            return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(userBean.telephoneNumber) : userBean.telephoneNumber != null)
            return false;
        if (username != null ? !username.equals(userBean.username) : userBean.username != null) return false;
        if (password != null ? !password.equals(userBean.password) : userBean.password != null) return false;
        if (email != null ? !email.equals(userBean.email) : userBean.email != null) return false;
        if (holderAccounts != null ? !holderAccounts.equals(userBean.holderAccounts) : userBean.holderAccounts != null)
            return false;
        if (accessAccounts != null ? !accessAccounts.equals(userBean.accessAccounts) : userBean.accessAccounts != null)
            return false;
        return cards != null ? cards.equals(userBean.cards) : userBean.cards == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (initials != null ? initials.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (bsn != null ? bsn.hashCode() : 0);
        result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (holderAccounts != null ? holderAccounts.hashCode() : 0);
        result = 31 * result + (accessAccounts != null ? accessAccounts.hashCode() : 0);
        result = 31 * result + (cards != null ? cards.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", initials='" + initials + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bsn='" + bsn + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", holderAccounts=" + holderAccounts +
                ", accessAccounts=" + accessAccounts +
                ", cards=" + cards +
                '}';
    }
}
