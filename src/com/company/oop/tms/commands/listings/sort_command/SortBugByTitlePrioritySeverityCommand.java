package com.company.oop.tms.commands.listings.sort_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortBugByTitlePrioritySeverityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public static final String THERE_NO_TASKS_SORT_MSG = "There are no Tasks to be sorted!";
    List<Bug> bugList;

    public SortBugByTitlePrioritySeverityCommand(SystemRepository systemRepository) {
        this.bugList = systemRepository.getBugList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return sortBugByTitlePrioritySeverity();
    }


    private String sortBugByTitlePrioritySeverity(){
        StringBuilder result = new StringBuilder();
        bugList
                .stream()
                .sorted(Comparator.comparing(Bug::getTitle).thenComparing(Bug::getPriority).thenComparing(Bug::getSeverity))
                .forEach(task -> {
                    result.append(task)
                            .append(System.lineSeparator())
                            .append("----------")
                            .append(System.lineSeparator());
                });
        if (result.isEmpty()) {
            System.out.println(THERE_NO_TASKS_SORT_MSG);
        }
        return result.toString().trim();
    }
}
