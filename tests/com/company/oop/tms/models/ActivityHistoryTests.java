package com.company.oop.tms.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

public class ActivityHistoryTests {
    //Todo
    @Test
    public void ActivityHistory_Print_Correct_Data(){
        ActivityHistoryImpl activityHistory = new ActivityHistoryImpl("Description");

        Assertions.assertEquals(activityHistory.getDescription() +
                " [" + activityHistory.getTimeStamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "]"
                ,activityHistory.toString());
    }


}
