package nl.springbank.helper.jsonrpc;

/**
 * @author Tristan de Boer
 */
public class JsonRpcResponse extends JsonRpc {

    private Object result;

    private ErrorObject error;

    public JsonRpcResponse(Object result, ErrorObject error) {
        this.result = result;
        this.error = error;
    }

    public JsonRpcResponse(Object result, ErrorObject error, String id) {
        super(id);
        this.result = result;
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ErrorObject getError() {
        return error;
    }

    public void setError(ErrorObject error) {
        this.error = error;
    }
}
