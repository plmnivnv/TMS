package com.company.oop.tms.models.tasks.contracts;

import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Commentable;

public interface Task extends Identifiable, Commentable {
    void addComment(Comment comment);

    void removeComment(Comment comment);
}
