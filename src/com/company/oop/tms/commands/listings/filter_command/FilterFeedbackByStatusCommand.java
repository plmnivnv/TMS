package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;
import com.company.oop.tms.models.tasks.enums.StatusStory;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class FilterFeedbackByStatusCommand implements Command {

    public static final String NO_SUCH_STATUS = "There is no Task with such status!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    SystemRepository systemRepository;
    List<Feedback> feedbackList;

    public FilterFeedbackByStatusCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
        feedbackList = systemRepository.getFeedbackList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        StatusFeedback targetStatus = ParsingHelpers.tryParseEnum(parameters.get(0), StatusFeedback.class);
        return filterStoryByStatus(targetStatus);
    }

    private String filterStoryByStatus(StatusFeedback targetStatus) {
        StringBuilder result = new StringBuilder();
        feedbackList
                .stream()
                .filter(b -> b.getStatusFeedback().equals(targetStatus))
                .forEach(task -> {
                    result.append(task)
                            .append(System.lineSeparator())
                            .append("----------")
                            .append(System.lineSeparator());
                });
        if (result.isEmpty()) {
            System.out.println(NO_SUCH_STATUS);
        }
        return result.toString().trim();
    }
}
