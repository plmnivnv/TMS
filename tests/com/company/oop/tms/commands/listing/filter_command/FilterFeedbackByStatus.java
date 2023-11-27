package com.company.oop.tms.commands.listing.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.commands.listings.filter_command.FilterFeedbackByStatusCommand;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.List;

public class FilterFeedbackByStatus {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private static final String VALID_FEEDBACK_NAME = "ValidFeedbackName";
    private static final String VALID_DESCRIPTION = "Description";
    private static final String VALID_STATUS_NAME = "Done";
    private static final int VALID_RATING = 6;

    SystemRepository systemRepository;
    Command command;

    @BeforeEach
    public void setup(){
        systemRepository = new SystemRepositoryImpl();
        command = new FilterFeedbackByStatusCommand(systemRepository);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        //Arrange
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> command.execute(parameters));
    }
    @Test
    public void execute_Should_NotChangeListSizes_When_Filtered(){
        //Arrange
        Feedback feedback = systemRepository.createFeedback(VALID_FEEDBACK_NAME,VALID_DESCRIPTION, VALID_RATING);

        List<String> parameters = List.of(VALID_STATUS_NAME);

        //Act
        command.execute(parameters);

        //Assert
        Assertions.assertEquals(1, systemRepository.getFeedbackList().size());
    }
}
