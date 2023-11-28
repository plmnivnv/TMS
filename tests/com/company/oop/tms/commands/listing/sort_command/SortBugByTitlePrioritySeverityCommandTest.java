package com.company.oop.tms.commands.listing.sort_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.commands.listings.sort_command.SortBugByTitlePrioritySeverityCommand;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class SortBugByTitlePrioritySeverityCommandTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    SystemRepository repository;
    Command command;

    @BeforeEach
    public void setup() {
        repository = new SystemRepositoryImpl();
        command = new SortBugByTitlePrioritySeverityCommand(repository);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount() {

        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }

    @Test
    public void execute_Should_NotChangeListSizes_When_Filtered() {
        List<String> parameters = new ArrayList<>();

        String result = command.execute(parameters);

        Assertions.assertEquals(0, result.length());
    }
}
