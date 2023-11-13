package com.company.oop.tms.models.tasks.enums;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    @Override
    public String toString() {
        switch (this) {
            case LOW:
                return "Low";
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            default:
                throw new IllegalArgumentException();
        }
    }
}
