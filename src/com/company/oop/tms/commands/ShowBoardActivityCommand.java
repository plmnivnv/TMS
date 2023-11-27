package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.ActivityHistoryImpl;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    SystemRepository systemRepository;

    public ShowBoardActivityCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Board boardName = systemRepository.findElementByName(systemRepository.getBoardList(), parameters.get(0), "Board");
        return showBoardActivity(boardName);
    }


    private String showBoardActivity(Board boardName){
        Board board = systemRepository.findElementByName(systemRepository.getBoardList(), boardName.getName(), "Board");
        List<ActivityHistoryImpl> taskList = board.getActivityHistoryList();
        StringBuilder result = new StringBuilder();
        for (ActivityHistoryImpl activityHistory : taskList) {
            result.append(activityHistory).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
