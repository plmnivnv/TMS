package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String BOARD_CREATE_MESSAGE = "Board with name %s was created";
    SystemRepository systemRepository;

    public CreateBoardCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        String teamName = parameters.get(1);
        return createBoard(name, teamName);
    }

    private String createBoard(String name, String teamName){
        Board board = systemRepository.createBoard(name);
        Team team = systemRepository.createTeam(teamName);
        team.addBoard(board);

        return String.format(BOARD_CREATE_MESSAGE, name);
    }
}
