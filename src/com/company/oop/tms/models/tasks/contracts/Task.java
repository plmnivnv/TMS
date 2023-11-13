package com.company.oop.tms.models.tasks.contracts;

import com.company.oop.tms.models.contracts.ActivityHistory;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Commentable;
import com.company.oop.tms.models.contracts.Displayable;

public interface Task extends Identifiable, Commentable, Displayable {
    void addComment(Comment comment);

    void removeComment(Comment comment);

}
