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
    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccountBean bankAccount;

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
    public String toString() {
        return "CardBean{" +
                "cardId=" + cardId +
                ", bankAccount=" + bankAccount +
                ", cardNumber=" + cardNumber +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
