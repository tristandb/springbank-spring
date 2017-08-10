package nl.springbank.objects;

import nl.springbank.bean.UserBean;
import nl.springbank.helper.AuthenticationHelper;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class AuthenticationObject {
    private String authToken;

    public AuthenticationObject(UserBean user) {
        this.authToken = AuthenticationHelper.getAuthToken(user.getUserId());
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
