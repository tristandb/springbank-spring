package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class OpenedAccount {
    private String iBAN;

    private int pinCard;

    private int pinCode;

    public OpenedAccount(String iBAN, int pinCard, int pinCode) {
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

    public int getPinCard() {
        return pinCard;
    }

    public void setPinCard(int pinCard) {
        this.pinCard = pinCard;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
