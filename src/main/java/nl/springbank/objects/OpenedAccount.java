package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class OpenedAccount {
    private String iBAN;

    private String pinCard;

    private String pinCode;

    public OpenedAccount(String iBAN, String pinCard, String pinCode) {
        this.iBAN = iBAN;
        this.pinCard = pinCard;
        this.pinCode = pinCode;
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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
