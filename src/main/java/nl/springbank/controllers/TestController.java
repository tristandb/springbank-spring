package nl.springbank.controllers;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
@Service
@JsonRpcService("/test")
@AutoJsonRpcServiceImpl
public class TestController {
    public int multiply(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b){
        return a * b;
    }
}
