package nl.springbank.helper;

/**
 * ErrorObject for JSONRpc
 *
 * @author Tristan de Boer
 */
public class ErrorObject {
    private int code;

    private String message;

    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
