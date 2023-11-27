package com.company.oop.tms.commands.feedback_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.enums.StatusBug;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackStatusCommand implements Command {
    public static final String STATUS_CHANGE_MESSAGE = "Status of Feedback with ID %d changed to %s.";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeFeedbackStatusCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        StatusFeedback statusFeedback = ParsingHelpers.tryParseEnum(parameters.get(1), StatusFeedback.class);
        return changeFeedbackStatus(id, statusFeedback);
    }

    private String changeFeedbackStatus(int id, StatusFeedback statusFeedback) {
        Feedback feedback = systemRepository.findElementById(systemRepository.getFeedbackList(), id, "Feedback");
        feedback.changeStatus(statusFeedback);
        return String.format(STATUS_CHANGE_MESSAGE, feedback.getId(), feedback.getStatusFeedback());
    }
}
