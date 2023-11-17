package com.company.oop.tms.commands.feedback_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateFeedbackInBoardCommandTest {

    public static final String VALID_TITLE_NAME = "TitleTitle";
    public static final String VALID_DESCRIPTION_NAME = "Description";
    public static final String INVALID_RATING = "Rating";
    public static final String VALID_BOARD_NAME = "BoardName";
    public static final int VALID_RATING = 5;
    SystemRepository systemRepository;
    Command command;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;


    @BeforeEach
    public void setup(){
        this.systemRepository = new SystemRepositoryImpl();
        this.command = new CreateFeedbackInBoardCommand(systemRepository);
    }

    @Test
    public void execute_Should_ThrowException_When_NumberOfArgumentsIsInvalid(){
        //Arrange
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_RatingIsNotNumber(){
        //Arrange,Act
        List<String> parameters = List.of(VALID_TITLE_NAME, VALID_DESCRIPTION_NAME, INVALID_RATING, VALID_BOARD_NAME);
        //Assert
        Assertions.assertThrows(NumberFormatException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_CreateFeedbackInBoard_When_ArgumentsAreValid(){
        //Arrange
        Board board = systemRepository.createBoard(VALID_BOARD_NAME);
        List<String> parameters = new ArrayList<>();
        parameters.add(VALID_TITLE_NAME);
        parameters.add(VALID_DESCRIPTION_NAME);
        parameters.add(String.valueOf(VALID_RATING));
        parameters.add(board.getName());
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, systemRepository.getFeedbackList().size()),
                ()-> Assertions.assertEquals(1, systemRepository.getTaskList().size()),
                ()-> Assertions.assertEquals(VALID_TITLE_NAME, systemRepository.getTaskList().get(0).getTitle()),
                ()-> Assertions.assertEquals(VALID_DESCRIPTION_NAME, systemRepository.getTaskList().get(0).getDescription()),
                ()-> Assertions.assertEquals(VALID_RATING, systemRepository.getFeedbackList().get(0).getRating()));
    }
}
