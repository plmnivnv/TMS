package com.company.oop.tms.commands.feedback_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangeFeedbackRatingCommandTest {

    public static final String VALID_RATING = "5";
    public static final String INVALID_ID = "InvalidID";
    public static final String VALID_ID = "1";
    public static final String INVALID_RATING = "InvalidRating";
    SystemRepository systemRepository;
    Command command;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    @BeforeEach
    public void setup(){
        this.systemRepository = new SystemRepositoryImpl();
        this.command = new ChangeFeedbackRatingCommand(systemRepository);
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
        List<String> parameters = List.of(INVALID_ID, VALID_RATING);
        //Act,Assert
        Assertions.assertThrows(NumberFormatException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_RatingIsNotNumber(){
        //Arrange
        List<String> parameters = List.of(VALID_ID, INVALID_RATING);
        //Act,Assert
        Assertions.assertThrows(NumberFormatException.class, ()-> command.execute(parameters));
    }

    @Test
    public void execute_Should_ChangeFeedbackRating_When_ArgumentsAreValid(){
        //Arrange
        systemRepository.createFeedback("ValidTitle", "ValidDescription", 2);
        List<String> parameters = new ArrayList<>();
        int feedbackId = 1;
        int ratingToChange = 10;
        parameters.add(String.valueOf(feedbackId));
        parameters.add(String.valueOf(ratingToChange));
        //Act
        command.execute(parameters);
        //Assert
        Assertions.assertEquals(10, systemRepository.findElementById(systemRepository.getFeedbackList(), feedbackId, "Feedback").getRating());
    }

}
