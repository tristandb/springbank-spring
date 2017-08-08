package nl.springbank.objects;

import nl.springbank.helper.AuthenticationHelper;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class AuthTokenObject {
    private String authToken;

    public AuthTokenObject(int userId) {
        this.authToken = AuthenticationHelper.getAuthToken(userId);
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
