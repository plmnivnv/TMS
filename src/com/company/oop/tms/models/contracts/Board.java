package com.company.oop.tms.models.contracts;

import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.List;

public interface Board extends Nameable, Displayable {

    List<Task> getTask();
    void addTask(Task task);
    void removeTask(Task task);
    void logActivityHistory(String activity);
}
