package com.company.oop.tms.models.tasks;

import com.company.oop.tms.models.ActivityHistoryImpl;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class TasksImpl implements Task {


    private static final String NO_COMMENT = "There is no comments!";
    private static final String COMMENT_NOT_FOUND = "There is no such comment!";
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final String TITLE_LENGTH_MSG = String.format("Title name must be between %d and %d symbols ",
            TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final String DESCRIPTION_LENGTH_MSG = String.format("Description name must be between %d and %d symbols",
            DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);
    private final List<Comment> commentList = new ArrayList<>();
    private final List<ActivityHistoryImpl> activityHistoryList = new ArrayList<>();
    private final int id;
    private String title;
    private String description;

    protected TasksImpl(int id, String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title,
                TITLE_MIN_LENGTH,
                TITLE_MAX_LENGTH,
                TITLE_LENGTH_MSG);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description,
                DESCRIPTION_MIN_LENGTH,
                DESCRIPTION_MAX_LENGTH,
                DESCRIPTION_LENGTH_MSG);
        this.description = description;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(commentList);
    }

    public List<ActivityHistoryImpl> getActivityHistoryList() {
        return new ArrayList<>(activityHistoryList);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        if (commentList.isEmpty()) {
            throw new IllegalArgumentException(NO_COMMENT);
        }
        for (Comment comment1 : commentList) {
            if (!comment1.equals(comment)) {
                throw new IllegalArgumentException(COMMENT_NOT_FOUND);
            }
        }
        commentList.remove(comment);
    }

    public void logActivityHistory(String activity) {
        activityHistoryList.add(new ActivityHistoryImpl(activity));
    }
}
