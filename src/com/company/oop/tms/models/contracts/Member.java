package com.company.oop.tms.models.contracts;

import com.company.oop.tms.models.tasks.contracts.Task;

public interface Member extends Nameable, Displayable {

    void assignTask(Task task);
    void unAssignTask(Task task);


}
