package nl.springbank.objects;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProvideAccessObject extends IbanAuthTokenObject {
    private String username;

    public ProvideAccessObject(int userId, String iBAN) {
        super(userId, iBAN);
    }

    public ProvideAccessObject(int userId, String iBAN, String username) {
        super(userId, iBAN);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
