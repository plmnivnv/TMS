package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 10;
    private static final String NAME_LENGTH_ERROR = String.format("Board name must be between %d and %d symbols",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private String name;
    private List<Task> taskList;
    private List<ActivityHistoryImpl> activityHistoryList;

    public BoardImpl(String name) {
        setName(name);
        taskList = new ArrayList<>();
        activityHistoryList = new ArrayList<>();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getTask() {
        return new ArrayList<>(taskList);
    }

    @Override
    public List<ActivityHistoryImpl> getActivityHistoryList() {
        return new ArrayList<>(activityHistoryList);
    }

    public void logActivityHistory(String activity) {
        activityHistoryList.add(new ActivityHistoryImpl(activity));
    }

    @Override
    public void addTask(Task task) {
        taskList.add(task);
        logActivityHistory(String.format("Task with ID: %d added to board", task.getId()));
    }

    @Override
    public void removeTask(Task task) {
        taskList.remove(task);
        logActivityHistory(String.format("Task with ID: %d removed from board", task.getId()));
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_LENGTH_ERROR);
        this.name = name;
    }
}
