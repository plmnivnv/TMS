package com.company.oop.tms.models.tasks;

import com.company.oop.tms.models.contracts.ActivityHistory;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TasksImpl implements Bug {
    public static final StatusBug INITIAL_STATUS = StatusBug.ACTIVE;
    private List<String> stepsToProduce;
    private Priority priority;
    private Severity severity;
    private StatusBug statusBug;
    private final Member assignee;


    public BugImpl(int id, String title, String description,
                   List<String> stepsToProduce,
                   Priority priority,
                   Severity severity,
                   Member assignee) {
        super(id, title, description);
        this.stepsToProduce = stepsToProduce;
        this.priority = priority;
        this.severity = severity;
        this.statusBug = StatusBug.ACTIVE;
        this.assignee = assignee;
    }

// по-добра идея е да връща мембър не стринга
    @Override
    public Member getAssignee(){
        return assignee;
    }

    @Override
    public StatusBug getStatusBug() {
        return statusBug;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }


    @Override
    public void changeStatusBug(StatusBug statusBug) {
        if(this.statusBug.equals(INITIAL_STATUS)){
            throw new IllegalArgumentException(String.format("Status is already %s",statusBug));
        }
        logActivityHistory(String.format("Status changed from %s to %s", statusBug, getStatusBug()));
        this.statusBug = statusBug;
    }

    @Override
    public void changePriorityBug(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void changeSeverityBug(Severity severity) {
        this.severity = severity;
    }
}
