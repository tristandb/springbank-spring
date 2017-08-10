package nl.springbank.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the application.
 *
 * @author Tristan de Boer
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter = new AutoJsonRpcServiceImplExporter();
        return autoJsonRpcServiceImplExporter;
    }
}
