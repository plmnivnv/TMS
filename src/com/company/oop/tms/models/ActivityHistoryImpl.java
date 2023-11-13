package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.ActivityHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityHistoryImpl implements ActivityHistory {
//TODO
   private String description;
   private LocalDateTime timeStamp = LocalDateTime.now();

    public ActivityHistoryImpl(String description){
        this.description = description;
    }


    @Override
    public String showHistory() {
        return String.format("""
                %s
                %s
                """,description,timeStamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
