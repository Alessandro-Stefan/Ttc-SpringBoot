package com.ttc.app.util.constants;

public enum PriorityConstants {
    VERY_LOW (0),
    LOW (1),
    MEDIUM (2),
    HIGH (3),
    VERY_HIGH (4);

    private final Integer level;

    PriorityConstants(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
