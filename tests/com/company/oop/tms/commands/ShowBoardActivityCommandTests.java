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

public class ShowBoardActivityCommandTests {
    private final int VALID_BOARD_NAME_LENGTH = 5;
    private final String VALID_BOARD_NAME = TestUtils.getString(VALID_BOARD_NAME_LENGTH + 1);

    private SystemRepository systemRepository;
    private List<String> args;

    @BeforeEach
    public void createRepo() {
        systemRepository = new SystemRepositoryImpl();
        args = new ArrayList<>();
        args.add(VALID_BOARD_NAME);
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Command showBoardActivity = new ShowBoardActivityCommand(systemRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showBoardActivity.execute(new ArrayList<>());
        });
    }
    @Test
    public void execute_Should_ShowBoardActivity_When_ValidParameters() {
        Command showBoardActivity = new ShowBoardActivityCommand(systemRepository);
        Board board = systemRepository.createBoard(VALID_BOARD_NAME);


        Assertions.assertDoesNotThrow(() -> showBoardActivity.execute(args));
    }

}
