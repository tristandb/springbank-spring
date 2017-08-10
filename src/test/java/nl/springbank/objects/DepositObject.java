package nl.springbank.objects;

public class DepositObject {
    private String iBAN;
    private String pinCard;
    private String pinCode;
    private double amount;

    public DepositObject(String iBAN, String pinCard, String pinCode, double amount) {
        this.iBAN = iBAN;
        this.pinCard = pinCard;
        this.pinCode = pinCode;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
