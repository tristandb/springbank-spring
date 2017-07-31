package nl.springbank.objects;

import nl.springbank.bean.UsernameIbanBean;

/**
 * @author Sven Konings
 */
public class UserAccessObject {

    private String iBAN;
    private String owner;

    public UserAccessObject(UsernameIbanBean usernameIbanBean) {
        this.iBAN = usernameIbanBean.getiBAN();
        this.owner = usernameIbanBean.getUsername();
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
