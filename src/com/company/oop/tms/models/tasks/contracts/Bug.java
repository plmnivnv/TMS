package com.company.oop.tms.models.tasks.contracts;

import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;

public interface Bug extends Task {
    String getAssignee();
    StatusBug getStatusBug();
    Priority getPriority();
    Severity getSeverity();

    void changeStatusBug(StatusBug statusBug);
    void changePriorityBug(Priority priority);
    void changeSeverityBug(Severity severity);
}
