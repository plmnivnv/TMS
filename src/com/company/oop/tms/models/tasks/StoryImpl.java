package com.company.oop.tms.models.tasks;

import com.company.oop.tms.models.contracts.ActivityHistory;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.models.tasks.enums.StatusStory;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TasksImpl implements Story {

    private Priority priority;
    private Size size;
    private StatusStory statusStory;
    private final Member assignee;



    public StoryImpl(int id, String title, String description, Priority priority, Size size,Member assignee) {
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
        logActivityHistory(String.format("The status of item with ID: %d changed from %s to %s", getId(), statusStory, getStatus()));
        this.statusStory = statusStory;
    }

    @Override
    public void changePriority(Priority priority) {
        logActivityHistory(String.format("The priority of item with ID: %d changed from %s to %s", getId(), priority, getPriority()));
        this.priority = priority;
    }

    @Override
    public void changeSize(Size size) {
        logActivityHistory(String.format("The size of item with ID: %d changed from %s to %s",getId(), size, getSize()));
        this.size = size;
    }
}
