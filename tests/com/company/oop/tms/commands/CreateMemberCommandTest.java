package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.contracts.Member;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;
import java.util.List;

public class CreateMemberCommandTest {
    @Test
    public void testExecuteWithValidArguments() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        CreateMemberCommand command = new CreateMemberCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("John Doe");

        String result = command.execute(parameters);

        Assertions.assertEquals(String.format(CreateMemberCommand.MEMBER_CREATE_MESSAGE, "John Doe"), result);
    }

    @Test
    public void testExecuteWithInvalidNumberOfArguments() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        CreateMemberCommand command = new CreateMemberCommand(systemRepository);

        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test(expected = NoSuchElementException.class)
    public void should_ThrowException_When_MemberIsPartOfMember() {
        SystemRepository repository = new SystemRepositoryImpl();
        Command command = new CreateNewTeamCommand(repository);

        Member member = new MemberImpl("John Doe");

        repository.createMember(member.getName());

        List<String> param = new ArrayList<>();
        param.add(member.getName());
        command.execute(param);

    }
}
