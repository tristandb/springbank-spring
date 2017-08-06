package nl.springbank.objects;

import nl.springbank.bean.CardBean;
import nl.springbank.bean.IbanBean;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class OpenedAccountObject extends OpenedCardObject {
    private String iBAN;

    public OpenedAccountObject(IbanBean ibanBean, CardBean cardBean) {
        super(cardBean);
        this.iBAN = ibanBean.getIban();
    }

    public String getiBAN() {
        return iBAN;
    }

    public void setiBAN(String iBAN) {
        this.iBAN = iBAN;
    }
}
