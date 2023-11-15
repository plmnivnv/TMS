package com.company.oop.tms.commands.feedback_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackRatingCommand implements Command {
    public static final String RATING_CHANGE_MESSAGE = "Rating of feedback with ID %d was changed to %d";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeFeedbackRatingCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        int rating = Integer.parseInt(parameters.get(1));
        return changeFeedbackRating(id, rating);
    }

    private String changeFeedbackRating(int id, int rating) {
        Feedback feedback = systemRepository.findElementById(systemRepository.getFeedbackList(), id, "Feedback");
        feedback.changeRating(rating);
        return String.format(RATING_CHANGE_MESSAGE, feedback.getId(), feedback.getRating());
    }
}
