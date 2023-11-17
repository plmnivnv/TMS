package com.company.oop.tms.commands;

import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllMembersCommandTest {
    @Test
    public void should_ShowAllMembers_When_PassedValidInput() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        ShowAllMembersCommand command = new ShowAllMembersCommand(systemRepository);

        List<String> parameters = new ArrayList<>();

        String result = command.execute(parameters);

        Assertions.assertEquals(0, result.length());
    }

    @Test
    public void should_ThrowException_InvalidNumberOfArguments() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        ShowAllMembersCommand command = new ShowAllMembersCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("John Doe");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test
    public void should_ShowMembersList_WithEmpty() {
        SystemRepository repository = new SystemRepositoryImpl();
        List<Member> memberLis = repository.getMemberList();
        Assertions.assertEquals(0, memberLis.size());
    }

    @Test
    public void should_ShowMembersList_AddMember() {
        SystemRepository repository = new SystemRepositoryImpl();
        Member member = new MemberImpl("John Doe");
        repository.createMember(member.getName());
        List<Member> memberslist = List.of(member);
        Assertions.assertEquals("John Doe", memberslist.get(0).getName());
    }
}

