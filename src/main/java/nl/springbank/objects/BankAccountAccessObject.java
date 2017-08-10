package nl.springbank.objects;

import nl.springbank.bean.UserBean;

/**
 * @author Sven Konings
 */
public class BankAccountAccessObject {
    private String userName;

    public BankAccountAccessObject(UserBean userBean) {
        this.userName = userBean.getUsername();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
