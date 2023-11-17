package com.company.oop.tms.commands;

import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.BoardImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Team;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardCommandTest {
    @Test
    public void should_CreateBoardCommand_When_PassedValidInput() {
        SystemRepository repository = new SystemRepositoryImpl();
        CreateBoardCommand command = new CreateBoardCommand(repository);

        Board board = new BoardImpl("Prod Board");
        Team team = new TeamImpl("Dev Team");

        List<String> parameters = new ArrayList<>();
        parameters.add(board.getName());
        parameters.add(team.getName());
        repository.createBoard(board.getName());
        repository.createTeam(team.getName());

        String result = command.execute(parameters);

        Assertions.assertEquals(String.format(CreateBoardCommand.BOARD_CREATE_MESSAGE
                , board.getName()), result);
    }

    @Test
    public void testExecuteWithInvalidNumberOfArguments() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        CreateBoardCommand command = new CreateBoardCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("Team Board");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test(expected = InvalidUserInputException.class)
    public void should_ThrowException_When_ExistingBoard() {
        SystemRepository repository = new SystemRepositoryImpl();
        CreateBoardCommand command = new CreateBoardCommand(repository);

        Board board = new BoardImpl("Product Development Board");
        repository.createBoard(board.getName());

        List<String> param = new ArrayList<>();
        param.add(board.getName());

        command.execute(param);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ExistingTeam() {
        SystemRepository repository = new SystemRepositoryImpl();
        CreateBoardCommand command = new CreateBoardCommand(repository);

        Team team = new TeamImpl("Dev Team");
        repository.createTeam(team.getName());

        List<String> param = new ArrayList<>();
        param.add(team.getName());

        command.execute(param);
    }
}
