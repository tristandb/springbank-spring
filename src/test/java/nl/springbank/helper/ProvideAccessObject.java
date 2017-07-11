package nl.springbank.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Tristan de Boer.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProvideAccessObject {
    private String authToken;
    private String iBAN;
    private String username;

    public ProvideAccessObject(String authToken, String iBAN, String username) {
        this.authToken = authToken;
        this.iBAN = iBAN;
        this.username = username;
    }

    public ProvideAccessObject(String authToken, String iBAN) {
        this.authToken = authToken;
        this.iBAN = iBAN;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
