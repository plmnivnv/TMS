package com.company.oop.tms;

import com.company.oop.tms.models.ActivityHistoryImpl;
import com.company.oop.tms.models.contracts.ActivityHistory;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;

import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void main(String[] args) {
        List<String> steps = new ArrayList<>();
        steps.add("test");
        Bug task = new BugImpl(1,"Testsdasdas","Testasdasdad",steps, Priority.HIGH, Severity.CRITICAL, StatusBug.ACTIVE);
        task.changePriorityBug(Priority.MEDIUM);

        System.out.println(task.getPriority());
    }

}
