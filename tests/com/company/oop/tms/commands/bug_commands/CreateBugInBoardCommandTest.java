package com.company.oop.tms.commands.bug_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;


public class CreateBugInBoardCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;

    public static final String VALID_DESCRIPTION = "Description";
    public static final String VALID_MEMBER_NAME = "MemberName";
    public static final String VALID_BOARD_NAME = "BoardName";
    public static final String INVALID_MEMBER_NAME = "InvalidMemberName";
    public static final String INVALID_SEVERITY = "invalidseverity";
    public static final String VALID_BUG_NAME = "ValidBugName";
    SystemRepository systemRepository;
    Command command;

    @BeforeEach
    public void setup() {
        systemRepository = new SystemRepositoryImpl();
        command = new CreateBugInBoardCommand(systemRepository);
    }

    @Test
    public void execute_Should_ThrowException_When_ArgumentsCountDifferentThanExpected(){
        //Arrange
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_PriorityIsInvalid(){
        //Arrange
        List<String> steps = new ArrayList<>();
        Severity element2 = Severity.CRITICAL;
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        List<String> parameters = List.of("5",VALID_DESCRIPTION,steps.toString(),"invalid priority",element2.toString(),member.getName(),VALID_BOARD_NAME);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_SeverityIsInvalid(){
        //Arrange
        List<String> steps = new ArrayList<>();
        Priority element = Priority.LOW;
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        List<String> parameters = List.of("5",VALID_DESCRIPTION,steps.toString(),element.toString(), INVALID_SEVERITY,member.getName(),VALID_BOARD_NAME);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_MemberNotValid(){
        //Arrange
        List<String> steps = new ArrayList<>();
        Priority element = Priority.LOW;
        Severity element2 = Severity.CRITICAL;
        List<String> parameters = List.of("5",VALID_DESCRIPTION,steps.toString(),element.toString(), element2.toString(), INVALID_MEMBER_NAME,VALID_BOARD_NAME);
        //Act,Assert
        Assertions.assertThrows(NoSuchElementException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_CreateBugInBoard_When_ArgumentsAreValid(){
        //Arrange
        List<String> steps = new ArrayList<>();
        Member member = systemRepository.createMember(VALID_MEMBER_NAME);
        Board board = systemRepository.createBoard(VALID_BOARD_NAME);
        Priority element = Priority.LOW;
        Severity element2 = Severity.CRITICAL;
        List<String> parameters = List.of(VALID_BUG_NAME, VALID_DESCRIPTION, steps.toString(), element.toString(), element2.toString(),member.getName(), VALID_BOARD_NAME);
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, systemRepository.getBugList().size()),
                ()-> Assertions.assertEquals(1, systemRepository.getTaskList().size()),
                ()-> Assertions.assertEquals(VALID_BUG_NAME, systemRepository.getTaskList().get(0).getTitle()),
                ()-> Assertions.assertEquals(VALID_DESCRIPTION, systemRepository.getTaskList().get(0).getDescription())
        );

    }


}
