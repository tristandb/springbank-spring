package nl.springbank.helper;

/**
 * @author Tristan de Boer.
 */
public class CloseAccount extends AuthTokenObject {
    private String iBAN;

    public CloseAccount(String authToken, String iBAN) {
        super(authToken);
        this.iBAN = iBAN;
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }
}
