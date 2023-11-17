package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.List;

public class CreateStoryInBoardCommandTest {

    public static final String VALID_TITLE = "ValidTitle";
    public static final String VALID_DESCRIPTION = "ValidDescription";
    public static final String VALID_MEMEBER_NAME = "Gosho";
    public static final String VALID_BOARD = "ValidBoard";
    public static final String INVALID_PRIORITY = "invalidPriority";
    public static final String INVALID_SIZE = "InvalidSize";
    SystemRepository systemRepository;
    Command command;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    @BeforeEach
    public void setup(){
        this.systemRepository = new SystemRepositoryImpl();
        this.command = new CreateStoryInBoardCommand(systemRepository);
    }

    @Test
    public void execute_Should_ThrowException_When_ArgumentsCountsIsInvalid(){
        //Arrange
        List<String> parameters = (TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1));
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_PriorityIsInvalid(){
        //Arrange
        Size size = Size.LARGE;
        List<String> parameters = List.of(VALID_TITLE, VALID_DESCRIPTION, INVALID_PRIORITY,size.toString(), VALID_MEMEBER_NAME, VALID_BOARD);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_SizeIsInvalid(){
        //Arrange
        Priority priority = Priority.HIGH;
        List<String> parameters = List.of(VALID_TITLE, VALID_DESCRIPTION, priority.toString(), INVALID_SIZE, VALID_MEMEBER_NAME, VALID_BOARD);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }


    @Test
    public void execute_Should_CreateStoryInBoard_When_ArgumentsAreValid(){
        //Arrange
        Board board = systemRepository.createBoard(VALID_BOARD);
        Member member = systemRepository.createMember(VALID_MEMEBER_NAME);
        Priority priority = Priority.HIGH;
        Size size = Size.LARGE;
        List<String> parameters = List.of(VALID_TITLE, VALID_DESCRIPTION, priority.toString(), size.toString(), member.getName(), board.getName());
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, systemRepository.getTaskList().size()),
                ()-> Assertions.assertEquals(1, systemRepository.getStoryList().size()),
                ()-> Assertions.assertEquals(VALID_TITLE, systemRepository.getStoryList().get(0).getTitle()),
                ()-> Assertions.assertEquals(VALID_DESCRIPTION, systemRepository.getStoryList().get(0).getDescription()),
                ()-> Assertions.assertEquals(priority, systemRepository.findElementById(systemRepository.getStoryList(), 1, "Story").getPriority()),
                ()-> Assertions.assertEquals(size, systemRepository.findElementById(systemRepository.getStoryList(), 1, "Story").getSize()),
                ()-> Assertions.assertEquals(member.getName(), systemRepository.findElementById(systemRepository.getStoryList(), 1, "Story").getAssignee()));
    }



}
