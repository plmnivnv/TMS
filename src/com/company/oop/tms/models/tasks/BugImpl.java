package com.company.oop.tms.models.tasks;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;

import java.util.List;

public class BugImpl extends TasksImpl implements Bug {
    public static final StatusBug INITIAL_STATUS = StatusBug.ACTIVE;
    public static final String PRIORITY_CHANGED_MESSAGE = "Priority of Item with ID: %d was changed from %s to %s.";
    public static final String SEVERITY_CHANGED_MESSAGE = "Severity of Item with ID: %d was changed from %s to %s.";
    public static final String STATUS_CHANGED_MESSAGE = "Status of Item with ID: %d changed from %s to %s.";
    public static final String STATUS_ERROR_MESSAGE = "Status is already %s.";
    public static final String PRIORITY_ERROR_MESSAGE = "Priority is already at %s";
    public static final String SEVERITY_ERROR_MESSAGE = "Severity is already at %s";
    private final List<String> stepsToProduce;
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
        StatusBug currentStatus = getStatusBug();
        if(!this.statusBug.equals(INITIAL_STATUS)){
            throw new IllegalArgumentException(String.format(STATUS_ERROR_MESSAGE,statusBug));
        }
        this.statusBug = statusBug;
        assignee.logActivityHistory(String.format(STATUS_CHANGED_MESSAGE, getId(), currentStatus, getStatusBug()));
    }

    @Override
    public void changePriorityBug(Priority priority) {
        Priority currentPriority = getPriority();
        if (priority.equals(getPriority())){
            throw new InvalidUserInputException(String.format(PRIORITY_ERROR_MESSAGE,getPriority()));
        }
        this.priority = priority;
        assignee.logActivityHistory(String.format(PRIORITY_CHANGED_MESSAGE,getId(), currentPriority,getPriority()));
    }

    @Override
    public void changeSeverityBug(Severity severity) {
        Severity currentSeverity = getSeverity();
        if (severity.equals(getSeverity())){
            throw new InvalidUserInputException(String.format(SEVERITY_ERROR_MESSAGE,getSeverity()));
        }
        this.severity = severity;
        assignee.logActivityHistory(String.format(SEVERITY_CHANGED_MESSAGE,getId(),currentSeverity,getSeverity()));
    }

    @Override
    public String toString() {
        return String.format("""
               %s
               Assignee: %s
               Priority: %s
               Severity: %s
               Status: %s""",super.toString(),getAssignee().getName(),getPriority(), getSeverity(), getStatusBug());
    }
}
