package com.company.oop.tms.commands.listing.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.commands.listings.filter_command.FilterTasksByTitleCommand;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTasksByTitleCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String VALID_MEMBER_NAME = "MemberName";
    public static final String VALID_BUG_NAME = "ValidBugName";
    public static final String VALID_DESCRIPTION = "Description";

    SystemRepository repository;
    Command command;

    @BeforeEach
    public void setup(){
        repository = new SystemRepositoryImpl();
        command = new FilterTasksByTitleCommand(repository);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentCount(){
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }

    @Test
    public void execute_Should_NotChangeListSizes_When_Filtered(){
        Member member = repository.createMember(VALID_MEMBER_NAME);
        List<String> steps = new ArrayList<>();

        Bug bug = repository.createBug(VALID_BUG_NAME,VALID_DESCRIPTION,steps, Priority.LOW, Severity.MAJOR,member);

        List<String> parameters = List.of(bug.getTitle());

        command.execute(parameters);


        Assertions.assertEquals(1,parameters.size());
    }

}
