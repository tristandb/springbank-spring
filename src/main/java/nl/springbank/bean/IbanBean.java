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
    /** The bank account. */
    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "bank_account_id", unique = true, nullable = false)
    private BankAccountBean bankAccount;

    /** The iban associated with the account. */
    @Column(name = "iban", unique = true, nullable = false)
    private String iban;

    /*
     * Bean methods
     */
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IbanBean)) return false;

        IbanBean ibanBean = (IbanBean) o;

        if (bankAccount != null ? !bankAccount.equals(ibanBean.bankAccount) : ibanBean.bankAccount != null)
            return false;
        return iban != null ? iban.equals(ibanBean.iban) : ibanBean.iban == null;
    }

    @Override
    public int hashCode() {
        int result = bankAccount != null ? bankAccount.hashCode() : 0;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IbanBean{" +
                "bankAccount=" + bankAccount +
                ", iban='" + iban + '\'' +
                '}';
    }
}
