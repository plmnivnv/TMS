package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.ActivityHistory;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.tasks.BugImpl;
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
    private List<ActivityHistory> activityHistoryList;

    public BoardImpl(String name) {
        setName(name);
        taskList = new ArrayList<>();
        activityHistoryList = new ArrayList<>();
    }


    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_LENGTH_ERROR);
        this.name = name;
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
    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void removeTask(Task task) {
        taskList.remove(task);
    }
}
