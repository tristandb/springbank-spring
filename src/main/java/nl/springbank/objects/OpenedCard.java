package nl.springbank.objects;

import nl.springbank.bean.CardBean;
import nl.springbank.helper.CardHelper;

/**
 * @author Tristan de Boer.
 */
public class OpenedCard {

    private String pinCard;

    private String pinCode;

    public OpenedCard(CardBean cardBean) {
        this.pinCard = cardBean.getCardNumber();
        this.pinCode = cardBean.getPin();
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
