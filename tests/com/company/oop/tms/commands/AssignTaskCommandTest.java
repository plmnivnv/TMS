package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskCommandTest {

    SystemRepository repository;
    Command command;
    Member member;

    @BeforeEach
    public void setup() {
        repository = new SystemRepositoryImpl();
        command = new AssignTaskCommand(repository);
        member = new MemberImpl("John Doe");
    }

    @Test
    public void execute_Should_ValidArguments() {

        Bug bug = repository.createBug("This is a Bug Title",
                "This is a Description", null, Priority.LOW, Severity.MAJOR, member);
        int idBug = bug.getId();

        Member assignee = new MemberImpl("John Doe");

        List<String> parameters = List.of( String.valueOf(idBug),assignee.getName());
        repository.createMember(member.getName());

        String result = command.execute(parameters);

        Assertions.assertEquals(String.format(AssignTaskCommand.TASK_ASSIGNED, idBug, assignee.getName()), result);
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidNumberOfArguments() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        AssignTaskCommand command = new AssignTaskCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidTaskId() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        AssignTaskCommand command = new AssignTaskCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("-1");
        parameters.add("John Doe");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidAssigneeName() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        AssignTaskCommand command = new AssignTaskCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("1");
        parameters.add("Unknown User");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            command.execute(parameters);
        });
    }
}
