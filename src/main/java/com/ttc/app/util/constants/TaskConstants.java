package com.ttc.app.util.constants;

public enum TaskConstants {
    PRIORITY_VERY_LOW (0),
    PRIORITY_LOW (1),
    PRIORITY_MEDIUM (2),
    PRIORITY_HIGH (3),
    PRIORITY_VERY_HIGH (4),

    STATUS_TODO(0),
    STATUS_IN_PROGRESS(1),
    STATUS_COMPLETED(2);

    private final int value;

    TaskConstants(int value) {
        this.value = value;
    }

    public Integer getLevel() {
        return value;
    }
}
