package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugSeverityCommand implements Command {
    public static final String SEVERITY_CHANGE_MESSAGE = "Severity of Bug with ID %d changed to %s.";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeBugSeverityCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class);
        return changeBugSeverity(id, severity);
    }

    private String changeBugSeverity(int id, Severity severity) {
        Bug bug = systemRepository.findElementById(systemRepository.getBugList(), id, "Bug");
        bug.changeSeverityBug(severity);
        return String.format(SEVERITY_CHANGE_MESSAGE, bug.getId(), bug.getSeverity());
    }
}
