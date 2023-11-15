package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.StatusBug;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugStatusCommand implements Command {
    public static final String STATUS_CHANGE_MESSAGE = "Status of Bug with ID %d changed to %s.";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeBugStatusCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        StatusBug statusBug = ParsingHelpers.tryParseEnum(parameters.get(1), StatusBug.class);
        return changeBugStatus(id, statusBug);
    }

    private String changeBugStatus(int id, StatusBug statusBug) {
        Bug bug = systemRepository.findElementById(systemRepository.getBugList(), id, "Bug");
        bug.changeStatusBug(statusBug);
        return String.format(STATUS_CHANGE_MESSAGE, bug.getId(), bug.getStatusBug());
    }
}
