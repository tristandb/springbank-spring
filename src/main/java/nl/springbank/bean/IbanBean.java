package nl.springbank.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    // bank account identifier
    @Id
    @Column(name = "bank_account_id")
    private long bankAccountId;

    // The IBAN of the account
    @NotNull
    private String iban;
}
