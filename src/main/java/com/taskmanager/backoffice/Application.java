package com.taskmanager.backoffice;

import com.taskmanager.backoffice.config.security.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
