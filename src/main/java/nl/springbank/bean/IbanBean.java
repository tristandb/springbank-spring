package nl.springbank.bean;

import javax.persistence.*;

/**
 * Bean representing the iban table. Associates each bank account with an iban.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Entity
@Table(name = "iban")
public class IbanBean {
    /*
     * Table values
     */
    /** Iban identifier. */
    @Id
    @Column(name = "iban_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ibanId;

    /** The bank account. */
    @OneToOne
    @JoinColumn(name = "bank_account_id", unique = true)
    private BankAccountBean bankAccount;

    /** The iban associated with the account. */
    @Column(name = "iban", unique = true)
    private String iban;

    /*
     * Bean methods
     */
    public long getIbanId() {
        return ibanId;
    }

    public void setIbanId(long ibanId) {
        this.ibanId = ibanId;
    }

    public BankAccountBean getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountBean bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "IbanBean{" +
                "ibanId=" + ibanId +
                ", bankAccount=" + bankAccount +
                ", iban='" + iban + '\'' +
                '}';
    }
}
