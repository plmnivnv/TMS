package com.company.oop.tms.models.tasks;

import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class TasksImpl implements Task {


    private static final String NO_COMMENT = "There is no comments!";
    private static final String COMMENT_NOT_FOUND = "There is no such comment!";
    private final int id;
    private final List<Comment> commentList = new ArrayList<>();

    protected TasksImpl(int id) {
        this.id = id;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(commentList);
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
        if (commentList.isEmpty()){
                throw new IllegalArgumentException(NO_COMMENT);
            }
        for (Comment comment1 : commentList) {
            if (!comment1.equals(comment)){
                throw new IllegalArgumentException(COMMENT_NOT_FOUND);
            }
        }
        commentList.remove(comment);
    }
}
