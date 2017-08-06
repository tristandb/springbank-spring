package nl.springbank.helper;

import nl.springbank.bean.UserBean;

public class UserHelper {

    public static String getDisplayName(UserBean user) {
        return user.getInitials() + " " + user.getSurname();
    }
}
