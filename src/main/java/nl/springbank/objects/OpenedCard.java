package nl.springbank.objects;

/**
 * @author Tristan de Boer.
 */
public class OpenedCard {

    private String pinCard;

    private String pinCode;

    public OpenedCard(String pinCard, String pinCode) {
        this.pinCard = pinCard;
        this.pinCode = pinCode;
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
