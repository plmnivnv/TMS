package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.MemberImpl;
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


public class AddCommentToTaskCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String COMMENT_ADDED_MESSAGE = "Comment from %s added to task with ID: %d";


    SystemRepository systemRepository;
    Command command;

    Member member;

    @BeforeEach
    public void setup() {
        systemRepository = new SystemRepositoryImpl();
        command = new AddCommentToTaskCommand(systemRepository);
        member = new MemberImpl("John Doe");
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount() {
        List<String> parameters = TestUtils.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }

    @Test
    public void execute__Should_ThrowException_When_MissingAuthor() {
        int id = 1;
        List<String> parameters = List.of("", "This is content", String.valueOf(id));

        Assertions.assertThrows(NoSuchElementException.class, () -> command.execute(parameters));
    }

    @Test
    public void execute__Should_ThrowException_When_MissingContent() {
        int id = 1;
        List<String> parameters = List.of(member.getName(), "", String.valueOf(id));

        Assertions.assertThrows(NoSuchElementException.class, () -> command.execute(parameters));
    }

    @Test
    public void execute__Should_ThrowException_When_MissingIdt() {
        String Content = "This is a Content";
        List<String> parameters = List.of(member.getName(), "This is a Content", "");

        Assertions.assertThrows(NoSuchElementException.class, () -> command.execute(parameters));
    }

    @Test
    public void execute__Should_ValidArguments() {
        Bug bug = systemRepository.createBug("This is a Bug Title",
                "This is a Description",null,Priority.LOW,Severity.MAJOR,member);
        int idBug = bug.getId();

        List<String> parameters = List.of(member.getName(),"This is a comment.",String.valueOf(idBug));
        systemRepository.createMember(member.getName());

        String result = command.execute(parameters);

        Assertions.assertEquals(String.format(AddCommentToTaskCommand.COMMENT_ADDED_MESSAGE, member.getName(),idBug), result);
    }



    @Test
    public void execute_Should_ExecuteWithInvalidNumberOfArguments() {

        List<String> parameters = new ArrayList<>();
        parameters.add("John Doe");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test
    public void should_ThrowException_ExecuteWithInvalidAuthor() {
        SystemRepository repository = new SystemRepositoryImpl();
        Command comman = new AddCommentToTaskCommand(repository);

        List<String> parameters = List.of("Unknown User", "This is a comment.", "123");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test()
    public void should_ThrowException_ExecuteWithInvalidTaskId() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        AddCommentToTaskCommand command = new AddCommentToTaskCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("John Doe");
        parameters.add("This is a comment.");
        parameters.add("-1");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            command.execute(parameters);
        });
    }
}
