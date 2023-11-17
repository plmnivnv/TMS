package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.bug_commands.CreateBugInBoardCommandTest;
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

public class ChangeStoryPriorityCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_ID = "InvalidID";
    public static final String VALID_ID = "5";
    public static final String INVALID_PRIORITY = "INVALID_PRIORITY";
    public static final String VALID_STORY_NAME = "ValidStoryName";
    public static final String VALID_DESCRIPTION = "ValidDescription";
    public static final String VALID_MEMBER_NAME = "ValidMember";
    SystemRepository systemRepository;
    Command command;

    @BeforeEach
    public void setup(){
        this.systemRepository = new SystemRepositoryImpl();
        this.command = new ChangeStoryPriorityCommand(systemRepository);
    }


    @Test
    public void execute_Should_ThrowException_When_ArgumentsCountIsInvalid(){
        //Arrange
        List<String> parameters= TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_IdIsNotNumber(){
        //Arrange
        Priority priority = Priority.HIGH;
        List<String> parameters = List.of(INVALID_ID,priority.toString());
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_PriorityIsInvalid(){
        //Arrange
        List<String> parameters = List.of(VALID_ID, INVALID_PRIORITY);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ChangePriority_When_ArgumentsAreValid(){
        //Arrange
        Member member = systemRepository.createMember(VALID_MEMBER_NAME);
        systemRepository.createStory(VALID_STORY_NAME, VALID_DESCRIPTION,Priority.HIGH,Size.LARGE,member);
        Priority priority = Priority.LOW;
        List<String> parameters = List.of(String.valueOf(1), priority.toString());
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertEquals(priority, systemRepository.findElementById(systemRepository.getStoryList(), 1, "Story").getPriority());
    }


}
