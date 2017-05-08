package nl.springbank.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class CreateTransaction {
    @JsonProperty("transaction")
    private TransactionBean transactionBean;

    private String authentication;

    public TransactionBean getTransactionBean() {
        return transactionBean;
    }

    public void setTransactionBean(TransactionBean transactionBean) {
        this.transactionBean = transactionBean;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
