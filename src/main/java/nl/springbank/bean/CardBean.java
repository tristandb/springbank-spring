package nl.springbank.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * CardBean. Consists of a card.
 *
 * @author Tristan de Boer).
 */
public class CardBean {
    /*
        Private methods
     */
    // Card identifier
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardId;

    // The cards number
    @NotNull
    private int cardNumber;

    // The cards corresponding bank_account_id
    @Column(name = "bank_account_id")
    private int bankAccountId;

    // The cards expiration date
    @Column(name = "expiration_date")
    private Date expirationDate;

    /*
        Public methods
     */

    public CardBean() {

    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
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
                ", cardNumber=" + cardNumber +
                ", bankAccountId=" + bankAccountId +
                ", expirationDate=" + expirationDate +
                '}';
    }
}