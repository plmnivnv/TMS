package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class AssignTaskCommand implements Command {
    public static final String TASK_ASSIGNED = "Task with ID: %d was assigned to %s";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public AssignTaskCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        Member assignee = systemRepository.findElementByName(systemRepository.getMemberList(), parameters.get(1),"Assignee");
        return assignTask(id,assignee);
    }

    private String assignTask(int id,Member assignee) {
        Task task = systemRepository.findElementById(systemRepository.getTaskList(), id, "Task");
        Member member = systemRepository.findElementByName(systemRepository.getMemberList(), assignee.getName(), "Assignee");
        member.assignTask(task);
        return String.format(TASK_ASSIGNED, task.getId(), member.getName());
    }
}
