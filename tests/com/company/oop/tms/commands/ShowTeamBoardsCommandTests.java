package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.BoardImpl;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamBoardsCommandTests {
    private final int VALID_TEAM_NAME_LENGTH = 5;
    private final int VALID_BOARD_NAME_LENGTH = 10;

    private final String VALID_TEAM_NAME = TestUtils.getString(VALID_TEAM_NAME_LENGTH + 1);


    private SystemRepository systemRepository;
    private List<String> args;

    @BeforeEach
    public void createRepo() {
        systemRepository = new SystemRepositoryImpl();
        args = new ArrayList<>();
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Command showBoards = new ShowTeamBoardsCommand(systemRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showBoards.execute(new ArrayList<>());
        });
    }
    @Test
    public void execute_Should_ShowTeamBoard_When_ValidParameters() {
        Command showBoard = new ShowTeamBoardsCommand(systemRepository);
        Team team = systemRepository.createTeam(VALID_TEAM_NAME);


        Assertions.assertDoesNotThrow(() -> showBoard.execute(List.of(team.getName())));
    }
}
