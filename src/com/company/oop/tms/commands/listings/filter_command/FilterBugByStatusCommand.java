package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.StatusBug;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class FilterBugByStatusCommand implements Command {

    public static final String NO_SUCH_STATUS = "There is no Task with such status!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments";
    SystemRepository systemRepository;
    List<Bug> bugList;

    public FilterBugByStatusCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
        bugList = systemRepository.getBugList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        StatusBug targetStatus = ParsingHelpers.tryParseEnum(parameters.get(0), StatusBug.class);
        return filterBugByStatus(targetStatus);
    }

    private String filterBugByStatus(StatusBug targetStatus) {
        StringBuilder result = new StringBuilder();
        bugList
                .stream()
                .filter(b -> b.getStatusBug().equals(targetStatus))
                .forEach(task -> {
                    result.append(task).append(System.lineSeparator())
                            .append("----------")
                            .append(System.lineSeparator());
                });
        if (result.isEmpty()) {
            System.out.println(NO_SUCH_STATUS);
        }
        return result.toString().trim();
    }

}
