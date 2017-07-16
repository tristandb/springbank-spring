package nl.springbank.objects;

import nl.springbank.bean.TransactionBean;
import sun.util.calendar.CalendarSystem;

import java.util.TimeZone;

public class TransactionObject {
    private String sourceIBAN;
    private String targetIBAN;
    private String targetName;
    private String date;
    private double amount;
    private String description;

    public TransactionObject(TransactionBean transactionBean) {
        this.sourceIBAN = transactionBean.getSourceBankAccountIban();
        this.targetIBAN = transactionBean.getTargetBankAccountIban();
        this.targetName = transactionBean.getTargetName();
        this.date = ((Object) CalendarSystem.getGregorianCalendar().getCalendarDate(transactionBean.getDate().getTime(), TimeZone.getDefault())).toString();
        this.amount = transactionBean.getAmount();
        this.description = transactionBean.getMessage();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
