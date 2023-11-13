package com.company.oop.tms;

import com.company.oop.tms.models.ActivityHistoryImpl;
import com.company.oop.tms.models.contracts.ActivityHistory;

public class Startup {
    public static void main(String[] args) {
        ActivityHistory activityHistory =  new ActivityHistoryImpl("test");
        System.out.println(activityHistory.showHistory());
    }

}
