package nl.springbank.helper;

import nl.springbank.bean.UserBean;

/**
 * @author Sven Konings
 */
public class UserHelper {
    /**
     * Get the dislpay name of the given user.
     *
     * @param user the given user
     * @return the display name
     */
    public static String getDisplayName(UserBean user) {
        return user.getInitials() + " " + user.getSurname();
    }
}
