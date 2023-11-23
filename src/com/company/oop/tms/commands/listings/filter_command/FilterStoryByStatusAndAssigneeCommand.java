package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.StatusStory;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class FilterStoryByStatusAndAssigneeCommand implements Command {
    public static final String NO_SUCH_ASSIGNEE_STATUS = "There is no Task with such assignee and status!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    List<Story> storyList;

    public FilterStoryByStatusAndAssigneeCommand(SystemRepository systemRepository) {
        storyList = systemRepository.getStoryList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        StatusStory targetStatus = ParsingHelpers.tryParseEnum(parameters.get(0), StatusStory.class);
        String assignee = parameters.get(1);
        return filterStoryByStatusAndAssignee(targetStatus,assignee);
    }

    private String filterStoryByStatusAndAssignee(StatusStory targetStatus, String assignee) {
        StringBuilder result = new StringBuilder();
        storyList
                .stream()
                .filter(b -> b.getStatus().equals(targetStatus))
                .filter(b -> b.getAssignee().getName().equalsIgnoreCase(assignee))
                .forEach(task -> {
                    result.append(task).append(System.lineSeparator())
                            .append("----------")
                            .append(System.lineSeparator());
                });
        if (result.isEmpty()) {
            System.out.println(NO_SUCH_ASSIGNEE_STATUS);
        }
        return result.toString().trim();
    }
}
