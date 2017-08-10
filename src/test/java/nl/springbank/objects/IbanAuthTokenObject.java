package nl.springbank.objects;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class IbanAuthTokenObject extends AuthTokenObject {
    private String iBAN;

    public IbanAuthTokenObject(int userId, String iBAN) {
        super(userId);
        this.iBAN = iBAN;
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }
}
