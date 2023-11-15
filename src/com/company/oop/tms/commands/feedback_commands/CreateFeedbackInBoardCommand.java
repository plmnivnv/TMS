package com.company.oop.tms.commands.feedback_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class CreateFeedbackInBoardCommand implements Command {
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    public CreateFeedbackInBoardCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = Integer.parseInt(parameters.get(2));
        String boardName = parameters.get(3);
        return createFeedbackInBoard(title,description,rating,boardName);
    }
    private String createFeedbackInBoard(String title,String description, int rating,String boardName){
        Board board = systemRepository.findElementByName(systemRepository.getBoardList(),boardName,"Board");
        Feedback feedback = systemRepository.createFeedback(title,description,rating);
        board.addTask(feedback);

        return String.format("Feedback with ID %d crated in board %s",feedback.getId(),board.getName());
    }
}
