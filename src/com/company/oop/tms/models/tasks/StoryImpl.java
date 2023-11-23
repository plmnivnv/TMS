package com.company.oop.tms.models.tasks;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.models.tasks.enums.StatusStory;

import java.util.Comparator;

public class StoryImpl extends TasksImpl implements Story {


    public static final String STATUS_ERROR_MESSAGE = "Status is already %s.";

    public static final String PRIORITY_ERROR_MESSAGE = "Priority is already at %s";
    public static final String SIZE_ERROR_MESSAGE = "Size is already at %s";

    private Priority priority;
    private Size size;
    private StatusStory statusStory;
    private final Member assignee;


    public StoryImpl(int id, String title, String description, Priority priority, Size size, Member assignee) {
        super(id, title, description);
        this.priority = priority;
        this.size = size;
        this.statusStory = StatusStory.NOT_DONE;
        this.assignee = assignee;
    }

    @Override
    public String getAssignee() {
        return assignee.getName();
    }

    @Override
    public StatusStory getStatus() {
        return statusStory;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void changeStatusStory(StatusStory statusStory) {
        StatusStory currentStatus = getStatus();
        if (statusStory.equals(getStatus())) {
            throw new InvalidUserInputException(String.format(STATUS_ERROR_MESSAGE, getStatus()));
        }
        this.statusStory = statusStory;
        assignee.logActivityHistory(String.format("The status of item with ID: %d changed from %s to %s", getId(), currentStatus, getStatus()));
    }

    @Override
    public void changePriority(Priority priority) {
        Priority currentPriority = getPriority();
        if (priority.equals(getPriority())) {
            throw new InvalidUserInputException(String.format(PRIORITY_ERROR_MESSAGE, getPriority()));
        }
        this.priority = priority;
        assignee.logActivityHistory(String.format("The priority of item with ID: %d changed from %s to %s", getId(), currentPriority, getPriority()));
    }

    @Override
    public void changeSize(Size size) {
        Size currentSize = getSize();
        if (size.equals(getSize())) {
            throw new IllegalArgumentException(String.format(SIZE_ERROR_MESSAGE, getSize()));
        }
        this.size = size;
        assignee.logActivityHistory(String.format("The size of item with ID: %d changed from %s to %s", getId(), currentSize, getSize()));
    }



}
