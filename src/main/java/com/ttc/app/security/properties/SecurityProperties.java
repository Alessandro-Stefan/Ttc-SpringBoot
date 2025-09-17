package com.ttc.app.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "security.endpoints")
public class SecurityProperties {

    @ConfigurationProperties(prefix = "public")
    private String[] publicEndpoints;

    @ConfigurationProperties(prefix = "admin")
    private String[] adminEndpoints;

    @ConfigurationProperties(prefix = "user")
    private String[] userEndpoints;

    
}
