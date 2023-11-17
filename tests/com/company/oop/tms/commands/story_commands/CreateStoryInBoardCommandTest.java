package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.List;

public class CreateStoryInBoardCommandTest {

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


}
