package nl.springbank.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Interface for JsonRpc communication. Used for testing purposes.
 *
 * @author Tristan de Boer
 */
public class JsonRpc {

    @JsonProperty("jsonrpc")
    private String jsonRpc = "2.0";

    @JsonProperty("id")
    private String id = "1";

    public JsonRpc() {

    }

    public JsonRpc(String id) {
        this.id = id;
    }

    public String getJsonRpc() {
        return jsonRpc;
    }

    public void setJsonRpc(String jsonRpc) {
        this.jsonRpc = jsonRpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
