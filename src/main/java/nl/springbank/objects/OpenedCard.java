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
        this.pinCard = CardHelper.convertToString(cardBean.getCardNumber());
        this.pinCode = CardHelper.convertToString(cardBean.getPin());
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
