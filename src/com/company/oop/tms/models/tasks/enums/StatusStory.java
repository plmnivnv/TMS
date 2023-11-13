package com.company.oop.tms.models.tasks.enums;

public enum StatusStory {

    DONE,
    NOT_DONE,
    IN_PROGRESS;

    @Override
    public String toString() {
        switch (this){
            case DONE:
                return "Done";
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "InProgress";
            default:
                throw new IllegalArgumentException();
        }
    }
}
