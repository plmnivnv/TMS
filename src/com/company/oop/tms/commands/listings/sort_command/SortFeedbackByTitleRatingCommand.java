package com.company.oop.tms.commands.listings.sort_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortFeedbackByTitleRatingCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String THERE_NO_TASKS_SORT_MSG = "There are no Tasks to be sorted!";
    List<Feedback> feedbackList;

    public SortFeedbackByTitleRatingCommand(SystemRepository systemRepository) {
        this.feedbackList = systemRepository.getFeedbackList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return sortFeedbackByTitleRating();
    }

    private String sortFeedbackByTitleRating() {
        StringBuilder result = new StringBuilder();
        feedbackList
                .stream()
                .sorted(Comparator.comparing(Feedback::getTitle).thenComparing(Feedback::getRating))
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
