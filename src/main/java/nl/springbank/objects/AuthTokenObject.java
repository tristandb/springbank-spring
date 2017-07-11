package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class AuthTokenObject {
    private String authToken;

    public AuthTokenObject(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
