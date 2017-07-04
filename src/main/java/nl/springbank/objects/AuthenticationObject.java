package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class AuthenticationObject {
    private String authToken;

    public AuthenticationObject(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
