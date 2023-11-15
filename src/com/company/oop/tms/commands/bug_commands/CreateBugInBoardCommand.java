package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateBugInBoardCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    public static final String BUG_WITH_NAME_S_ADDED_TO_BOARD_S = "Bug with ID %d created in board %s";
    SystemRepository systemRepository;

    public CreateBugInBoardCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        List<String> stepsToProduce = Arrays.asList(parameters.get(2).split(","));
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
        Member assignee = systemRepository.findElementByName(systemRepository.getMemberList(),parameters.get(5), "Member");
        String boardName = parameters.get(6);
        return createBugInBoard(title,description,stepsToProduce, priority, severity, assignee,boardName);
    }


    private String createBugInBoard(String title, String description, List<String> stepsToProduce, Priority priority, Severity severity, Member assignee,String boardName){
        Board board = systemRepository.findElementByName(systemRepository.getBoardList(), boardName, "Board");
        Bug bug = systemRepository.createBug(title, description, stepsToProduce, priority, severity, assignee);
        board.addTask(bug);
        return String.format(BUG_WITH_NAME_S_ADDED_TO_BOARD_S, bug.getId(), board.getName());
    }



}
