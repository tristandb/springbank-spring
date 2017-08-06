package nl.springbank.objects;

import nl.springbank.bean.BankAccountBean;

/**
 * @author Sven Konings
 */
public class UserAccessObject {

    private String iBAN;
    private String owner;

    public UserAccessObject(BankAccountBean bankAccount) {
        this.iBAN = bankAccount.getIban().getIban();
        this.owner = bankAccount.getHolder().getUsername();
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
