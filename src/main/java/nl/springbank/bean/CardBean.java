package nl.springbank.bean;

import javax.persistence.*;
import java.sql.Date;

/**
 * Bean representing the card table. A card is associated with a bank account.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Entity
@Table(name = "card", uniqueConstraints = @UniqueConstraint(columnNames = {
        "bank_account_id", "card_number"
}))
public class CardBean {
    /*
     * Table values
     */
    /** Card identifier. */
    @Id
    @Column(name = "card_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardId;

    /** The bank account associated with the card. */
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccountBean bankAccount;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserBean user;

    /** The card number. */
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    /** The pin code of the card. */
    @Column(name = "pin", nullable = false)
    private String pin;

    /** The expiration date of the card. */
    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    /*
     * Bean methods
     */
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public BankAccountBean getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountBean bankAccount) {
        this.bankAccount = bankAccount;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardBean)) return false;

        CardBean cardBean = (CardBean) o;

        if (cardId != cardBean.cardId) return false;
        if (bankAccount != null ? !bankAccount.equals(cardBean.bankAccount) : cardBean.bankAccount != null)
            return false;
        if (user != null ? !user.equals(cardBean.user) : cardBean.user != null) return false;
        if (cardNumber != null ? !cardNumber.equals(cardBean.cardNumber) : cardBean.cardNumber != null) return false;
        if (pin != null ? !pin.equals(cardBean.pin) : cardBean.pin != null) return false;
        return expirationDate != null ? expirationDate.equals(cardBean.expirationDate) : cardBean.expirationDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (cardId ^ (cardId >>> 32));
        result = 31 * result + (bankAccount != null ? bankAccount.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "cardId=" + cardId +
                ", bankAccount=" + bankAccount +
                ", user=" + user +
                ", cardNumber='" + cardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
