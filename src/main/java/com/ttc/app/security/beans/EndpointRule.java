package com.ttc.app.security.beans;

import java.util.List;

public class EndpointRule {
    private String path;
    private String method;
    private List<String> methods;

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public List<String> getMethods() {
        return methods;
    }
    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
}
