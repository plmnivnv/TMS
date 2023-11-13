package com.company.oop.tms.models.tasks;

import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class TasksImpl implements Task {


    private final int id;
    private List<Comment> commentList;

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
}
