package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class IbanAuthTokenObject extends AuthTokenObject {
    private String iBAN;

    public IbanAuthTokenObject(String authToken, String iBAN) {
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
