package com.company.oop.tms.models.tasks.enums;

public enum StatusBugAndStory {


    ACTIVE,
    DONE,
    NOT_DONE,
    IN_PROGRESS;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            case NOT_DONE:
                return "Not Done";
            case IN_PROGRESS:
                return "In progress";
            default:
                throw new IllegalArgumentException();
        }
    }
}
