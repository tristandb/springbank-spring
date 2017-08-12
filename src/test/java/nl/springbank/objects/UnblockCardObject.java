package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class UnblockCardObject extends AuthTokenObject {
    private String iBAN;
    private String pinCard;

    public UnblockCardObject(int userId, String iBAN, String pinCard) {
        super(userId);
        this.iBAN = iBAN;
        this.pinCard = pinCard;
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getPinCard() {
        return pinCard;
    }

    public void setPinCard(String pinCard) {
        this.pinCard = pinCard;
    }
}
