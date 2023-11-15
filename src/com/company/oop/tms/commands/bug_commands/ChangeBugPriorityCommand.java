package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugPriorityCommand implements Command {

    public static final String PRIORITY_CHANGE_MESSAGE = "Priority of Bug with ID %d changed to %s.";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeBugPriorityCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        return changeBugPriority(id, priority);
    }

    private String changeBugPriority(int id, Priority priority) {
        Bug bug = systemRepository.findElementById(systemRepository.getBugList(), id, "Bug");
        bug.changePriorityBug(priority);
        return String.format(PRIORITY_CHANGE_MESSAGE, bug.getId(), bug.getPriority());
    }
}
