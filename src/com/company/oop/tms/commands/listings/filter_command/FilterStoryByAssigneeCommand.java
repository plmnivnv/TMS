package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class FilterStoryByAssigneeCommand implements Command {

    public static final String NO_SUCH_ASSIGNEE = "There is no Task with such assignee!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    List<Story> storyList;

    public FilterStoryByAssigneeCommand(SystemRepository systemRepository) {
        storyList = systemRepository.getStoryList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String assignee = parameters.get(0);
        return filterStoryByAssignee(assignee);
    }


    private String filterStoryByAssignee(String assignee) {
        StringBuilder result = new StringBuilder();
        storyList
                .stream()
                .filter(b -> b.getAssignee().getName().equalsIgnoreCase(assignee))
                .forEach(task -> {
                    result.append(task).append(System.lineSeparator())
                            .append("----------")
                            .append(System.lineSeparator());
                });
        if (result.isEmpty()) {
            System.out.println(NO_SUCH_ASSIGNEE);
        }
        return result.toString().trim();
    }
}
