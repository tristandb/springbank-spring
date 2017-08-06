package nl.springbank.helper;

import java.util.ArrayList;

/**
 * @author Tristan de Boer
 */
public class JsonRpcRequest extends JsonRpc {
    public String method;

    public Object params = new ArrayList<>();

    public JsonRpcRequest(String method, Object params) {
        super();
        this.method = method;
        this.params = params;
    }

    public JsonRpcRequest(String method, Object params, String id) {
        super(id);
        this.method = method;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
