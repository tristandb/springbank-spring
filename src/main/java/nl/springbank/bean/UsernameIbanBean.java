package nl.springbank.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * @author Tristan de Boer.
 *
 * SELECT b.account_id AS id, ub.username AS username, ib.iban AS iBAN, ub.user_id as user_id
    FROM bank_account b
    JOIN user_bank_account ubab ON ubab.bank_account_id = b.account_id
    JOIN iban ib ON ib.bank_account_id = b.account_id
    JOIN user ub ON ub.user_id = ubab.user_id
    UNION
    SELECT b.account_id AS id, ub.username AS username, ib.iban AS iBAN, ub.user_id AS user_id
    FROM bank_account b
    JOIN iban ib ON ib.bank_account_id = b.account_id
    JOIN user ub ON ub.user_id = b.holder_user_id
 */
@Entity
@Immutable
@Table(name = "username_iban")
public class UsernameIbanBean {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id = 0;

    @JsonIgnore
    private long userId;

    @Column(name = "iBAN", nullable = false)
    private String iBAN;

    @Column(nullable = false)
    private String username;

    public UsernameIbanBean() {
    }

    public UsernameIbanBean(String username, String iBAN) {
        this.iBAN = iBAN;
        this.username = username;
    }

    public UsernameIbanBean(long userId, String iBAN, String username) {
        this.userId = userId;
        this.iBAN = iBAN;
        this.username = username;
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
