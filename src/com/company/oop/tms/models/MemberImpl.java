package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberImpl implements Member {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERROR = String.format("Member name must be between %d and %d symbols",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    public static final String NO_TASKS_MESSAGE = "There is no tasks!";
    public static final String NON_EXISTING_TASK_MESSAGE = "There is no such task to remove!";

    private String name;
    private List<Task> taskList;
    private List<ActivityHistoryImpl> activityHistoryList;

    public MemberImpl(String name) {
        setName(name);
        taskList = new ArrayList<>();
        activityHistoryList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }



    public void setName(String name) {
        ValidationHelpers.validateStringLength(name,
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);
        this.name = name;
    }

    @Override
    public List<ActivityHistoryImpl> getActivityHistoryList() {
        return new ArrayList<>(activityHistoryList);
    }
    @Override
    public void assignTask(Task task){
        taskList.add(task);
    }

    @Override
    public void unAssignTask(Task task) {
        taskList.remove(task);
    }

    public void logActivityHistory(String activity) {
        activityHistoryList.add(new ActivityHistoryImpl(activity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return Objects.equals(taskList, member.taskList);
    }
}
