package nl.springbank.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@Entity
@Table(name = "iban")
public class IbanBean {
    /*
        Private methods
     */
    @Id
    @Column(name = "bank_account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bankAccountId;

    // The IBAN of the account
    @NotNull
    private String iban;


    public IbanBean() {
    }

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
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
                "bankAccountId=" + bankAccountId +
                ", iban='" + iban + '\'' +
                '}';
    }
}
