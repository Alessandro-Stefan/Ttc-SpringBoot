package com.ttc.app.security.properties;

import com.ttc.app.security.beans.EndpointRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "security.endpoints")
public class SecurityProperties {

    private List<EndpointRule> publicEndpoints;
    private List<EndpointRule> adminEndpoints;
    private List<EndpointRule> userEndpoints;
    @Value("${jwt.secret}")
    private String jwtKey;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public List<EndpointRule> getUserEndpoints() {
        return userEndpoints;
    }
    public void setUserEndpoints(List<EndpointRule> userEndpoints) {
        this.userEndpoints = userEndpoints;
    }
    public List<EndpointRule> getAdminEndpoints() {
        return adminEndpoints;
    }
    public void setAdminEndpoints(List<EndpointRule> adminEndpoints) {
        this.adminEndpoints = adminEndpoints;
    }
    public List<EndpointRule> getPublicEndpoints() {
        return publicEndpoints;
    }
    public void setPublicEndpoints(List<EndpointRule> publicEndpoints) {
        this.publicEndpoints = publicEndpoints;
    }
    public String getJwtKey() {
        return jwtKey;
    }
    public Long getJwtExpiration() {
        return jwtExpiration;
    }
}
