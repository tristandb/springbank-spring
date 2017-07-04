package nl.springbank.helper;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class GetBalanceObject {
    private String authToken;

    private String iBAN;

    public GetBalanceObject(String authToken, String iBAN) {
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
}
