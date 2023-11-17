package com.company.oop.tms.commands.feedback_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangeFeedbackStatusCommandTest {


    public static final String VALID_RATING = "5";
    public static final String INVALID_ID = "InvalidID";
    public static final String VALID_ID = "1";
    public static final String INVALID_RATING = "InvalidRating";
    public static final String INVALID_STATUS = "INVALID_STATUS";
    SystemRepository systemRepository;
    Command command;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    @BeforeEach
    public void setup(){
        this.systemRepository = new SystemRepositoryImpl();
        this.command = new ChangeFeedbackStatusCommand(systemRepository);
    }


    @Test
    public void execute_Should_ThrowException_When_InvalidNumberOfArgument(){
        //Arrange
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_IdIsNotNumber(){
        //Arrange
        StatusFeedback element = StatusFeedback.DONE;
        List<String> parameters = List.of(INVALID_ID, element.toString());
        //Act,Assert
        Assertions.assertThrows(NumberFormatException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_StatusIsInvalid(){
        //Arrange
        List<String> parameters = List.of(VALID_ID, INVALID_STATUS);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ChangeFeedbackStats_When_ArgumentsAreValid(){
        //Arrange
        systemRepository.createFeedback("ValidTitle", "ValidDescription", 2);
        List<String> parameters = new ArrayList<>();
        int feedbackId = 1;
        StatusFeedback statusFeedback = StatusFeedback.DONE;
        parameters.add(String.valueOf(feedbackId));
        parameters.add(statusFeedback.toString());
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertEquals(statusFeedback, systemRepository.findElementById(systemRepository.getFeedbackList(), feedbackId, "Feedback").getStatusFeedback());
    }
}
