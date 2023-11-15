package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamBoardsCommand implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String EMPTY_BOARD_MESSAGE = "No Boards in this team!";
    SystemRepository systemRepository;

    public ShowTeamBoardsCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        Team team = systemRepository.findElementByName(systemRepository.getTeamList(), parameters.get(0), "Team");
        return ShowTeamBoards(team);
    }

    private String ShowTeamBoards(Team team){
        Team teamName = systemRepository.findElementByName(systemRepository.getTeamList(), team.getName(), "Team");
        List<Board> boardList = teamName.getBoards();
        StringBuilder result = new StringBuilder();
        int index = 1;
        if (boardList.isEmpty()) {
            result.append(EMPTY_BOARD_MESSAGE);
        } else {
            for (Board board : boardList) {
                result.append(index).append(".").append(board.getName()).append(System.lineSeparator());
                index++;
            }
        }
        return result.toString().trim();
    }

}
