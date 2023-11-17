package com.company.oop.tms.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityHistoryImpl {
    //TODO
    private String description;
    private LocalDateTime timeStamp = LocalDateTime.now();

    public ActivityHistoryImpl(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return String.format("""
                %s [%s]""", description, timeStamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
