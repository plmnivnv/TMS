package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangeBugStatusCommandTest {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_STATUS_NAME = "InvalidStatusName";
    public static final String INVALID_ID = "StringNotNumber";
    public static final String VALID_ID = "5";

    SystemRepository systemRepository;
    Command command;

    @BeforeEach
    public void setup(){
        systemRepository = new SystemRepositoryImpl();
        command = new ChangeBugStatusCommand(systemRepository);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        //Arrange
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_IdIsNotNumber(){
        //Arrange
        StatusBug element = StatusBug.DONE;
        List<String> parameters = List.of(INVALID_ID,element.toString());
        //Act,Assert
        Assertions.assertThrows(NumberFormatException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_StatusIsInvalid(){
        //Arrange
        List<String> parameters = List.of(VALID_ID, INVALID_STATUS_NAME);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ChangePriority_When_InputIsValid(){
        //Arrange
        List<String> steps = new ArrayList<>();
        systemRepository.createBug(CreateBugInBoardCommandTest.VALID_BUG_NAME,
                CreateBugInBoardCommandTest.VALID_DESCRIPTION,
                steps,
                Priority.HIGH,
                Severity.MAJOR,
                systemRepository.createMember(CreateBugInBoardCommandTest.VALID_MEMBER_NAME));
        List<String> parameters = new ArrayList<>();
        int bugId = 1;
        StatusBug element = StatusBug.DONE;
        parameters.add(String.valueOf(bugId));
        parameters.add(element.toString());
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertEquals(element, systemRepository.findElementById(systemRepository.getBugList(), bugId, "Bug").getStatusBug());
    }
}
