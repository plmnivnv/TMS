package com.company.oop.tms.commands.listing.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.commands.listings.filter_command.FilterBugByAssigneeCommand;
import com.company.oop.tms.commands.listings.filter_command.FilterBugByStatusAndAssigneeCommand;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class FilterBugByStatusAndAssigneeCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public static final String VALID_BUG_NAME = "ValidBugName";

    public static final String VALID_MEMBER_NAME = "MemberName";

    public static final String VALID_DESCRIPTION = "Description";
    public static final String VALID_STATUS_NAME = "Done";

    SystemRepository systemRepository;
    Command command;

    @BeforeEach
    public void setup(){
        systemRepository = new SystemRepositoryImpl();
        command = new FilterBugByStatusAndAssigneeCommand(systemRepository);
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
        Member member = systemRepository.createMember(VALID_MEMBER_NAME);
        List<String> steps = new ArrayList<>();
        Bug bug = systemRepository.createBug(VALID_BUG_NAME,VALID_DESCRIPTION,steps, Priority.LOW, Severity.MAJOR,member);
        List<String> parameters = List.of(VALID_STATUS_NAME,VALID_MEMBER_NAME);

        //Act
        command.execute(parameters);

        //Assert
        Assertions.assertEquals(1, systemRepository.getBugList().size());
    }
}
