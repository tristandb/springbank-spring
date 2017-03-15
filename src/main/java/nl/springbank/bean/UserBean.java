package nl.springbank.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * UserBean.
 *
 * @author Tristan de Boer.
 */
@Entity
@Table(name = "user")
public class UserBean {
    /*
        Private methods
     */

    // Autogenerated id (unique for each user in the db)
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // The user's name
    @NotNull
    private String name;

    // The user's surname
    @NotNull
    private String surname;

    // The user's initials
    @NotNull
    private String initials;

    // The user's date of birth
    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    // The user's BSN (Burger Service Nummer, Social Security Number equivalent in the Netherlands)
    @NotNull
    private String bsn;

    // The user's street address
    @NotNull
    @Column(name = "street_address")
    private String streetAddress;

    // The user's telephone number
    @NotNull
    @Column(name = "telephone_number")
    private String telephoneNumber;

    // The user's email
    @NotNull
    private String email;

    /*
        Public methods
     */

    public UserBean() {}

    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", initials='" + initials + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bsn='" + bsn + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}