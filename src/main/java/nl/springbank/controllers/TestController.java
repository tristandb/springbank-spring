package nl.springbank.controllers;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.jsonrpc.OtherError;

/**
 * @author Tristan de Boer.
 */
@JsonRpcService("/api/test")
public interface TestController {
    /**
     * Multiplies two values.
     * @param a The first value
     * @param b The second value
     * @return a * b
     */
    int multiply(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b);

    /**
     * Throws an error.
     * @param a Some value
     * @param b Some value
     * @return
     * @throws OtherError
     */
    int throwsError(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b) throws OtherError;
}
