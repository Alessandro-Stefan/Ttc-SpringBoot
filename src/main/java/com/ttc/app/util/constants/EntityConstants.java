package com.ttc.app.util.constants;

public enum EntityConstants {
    DEFAULT_ENTITY (1),

    ADMIN_ROLE ("ROLE_ADMIN"),
    USER_ROLE ("ROLE_USER");

    private final Object value;

    EntityConstants(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
