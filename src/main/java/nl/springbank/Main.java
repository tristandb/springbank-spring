package nl.springbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main application of Spring. This file starts all other files.
 *
 * @author Tristan de Boer.
 */

@SpringBootApplication
public class Main {

    /**
     * Main class. Starts the application.
     * @param args Arguments passed to Spring.
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
