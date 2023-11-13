package com.company.oop.tms.models.tasks.enums;

public enum StatusBug {

    ACTIVE,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException();
        }
    }
}
