package com.taskmanager.backoffice.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {

    private String issuerInfo;
    private String superSecretKey;
    private Integer tokenExpirationTimeInMinutes;
}

