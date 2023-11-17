package com.company.oop.tms.commands;


import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddMemberToTeamCommandTest {

    SystemRepository repository;
    Command command;
    Member member;
    Team team;

    @BeforeEach
    public void setup(){
        repository = new SystemRepositoryImpl();
        command = new AddMemberToTeamCommand(repository);
        member = new MemberImpl("John Doe123");
        team = new TeamImpl("Dev Team");
    }


    @Test
    public void should_AddMemberToTeam_When_PassedValidInput() {

        repository.createMember(member.getName());
        repository.createTeam(team.getName());

        List<String> param = new ArrayList<>();
        param.add(member.getName());
        param.add(team.getName());
        String result = command.execute(param);

        Assertions.assertEquals(String.format(AddMemberToTeamCommand.MEMBER_ADDED_TO_TEAM_MESSAGE,
                member.getName(), team.getName()), result);

    }

    @Test
    public void should_ThrowException_When_MemberIsPartOfMember() {
        Command command = new CreateNewTeamCommand(repository);
        Member member = new MemberImpl("John Doe");
        repository.createMember(member.getName());
        List<String> param = new ArrayList<>();
        param.add(member.getName());
        Assertions.assertThrows(NoSuchElementException.class, () -> command.execute(param));
    }

    @Test
    public void should_ThrowException_When_MemberIsPartOfTeam() {
        Command command = new CreateNewTeamCommand(repository);
        Team team = new TeamImpl("Dev Team");
        repository.createTeam(team.getName());
        List<String> param = new ArrayList<>();
        param.add(team.getName());
        Assertions.assertThrows(NoSuchElementException.class, () -> command.execute(param));
    }

}
