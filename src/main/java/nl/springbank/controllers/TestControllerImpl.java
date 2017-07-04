package nl.springbank.controllers;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import nl.springbank.exceptions.jsonrpc.OtherError;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author Tristan de Boer.
 */
@Service
@AutoJsonRpcServiceImpl
public class TestControllerImpl implements TestController {
    /**
     * Multiplies two values.
     * @param a The first value
     * @param b The second value
     * @return a * b
     */
    @Override
    public int multiply(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b){
        return a * b;
    }

    /**
     * Throws an error.
     * @param a Some value
     * @param b Some value
     * @return
     * @throws OtherError
     */
    @Override
    @JsonRpcErrors({
            @JsonRpcError(exception = OtherError.class, code = 500, message = "An unexpected error occured, see error details.")
    })
    public int throwsError(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b) throws OtherError {
        throw new OtherError();
    }
}
