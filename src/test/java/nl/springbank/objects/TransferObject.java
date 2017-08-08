package nl.springbank.objects;

public class TransferObject extends AuthTokenObject {
    private String sourceIBAN;
    private String targetIBAN;
    private String targetName;
    private double amount;
    private String description;

    public TransferObject(int userId, String sourceIBAN, String targetIBAN, String targetName, double amount, String description) {
        super(userId);
        this.sourceIBAN = sourceIBAN;
        this.targetIBAN = targetIBAN;
        this.targetName = targetName;
        this.amount = amount;
        this.description = description;
    }

    public String getSourceIBAN() {
        return sourceIBAN;
    }

    public void setSourceIBAN(String sourceIBAN) {
        this.sourceIBAN = sourceIBAN;
    }

    public String getTargetIBAN() {
        return targetIBAN;
    }

    public void setTargetIBAN(String targetIBAN) {
        this.targetIBAN = targetIBAN;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
