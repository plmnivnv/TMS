package com.company.oop.tms.models.tasks.enums;

public enum StatusFeedback {

    NEW,
    UN_SCHEDULED,
    SCHEDULED,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "New";
            case UN_SCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException();
        }
    }
}
