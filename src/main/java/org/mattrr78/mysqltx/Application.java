package org.mattrr78.mysqltx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        int port = 6821;

        String portStr = System.getenv("PORT");
        if (portStr != null)  {
            port = Integer.parseInt(portStr);
        }

        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }
}
