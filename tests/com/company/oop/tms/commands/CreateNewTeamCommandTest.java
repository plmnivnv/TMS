package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.Team;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CreateNewTeamCommandTest {


    @Test
    public void should_AddMemberToTeam_When_PassedValidInput()  {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        CreateNewTeamCommand command = new CreateNewTeamCommand(systemRepository);

        List<String> parameters = new ArrayList<>();
        parameters.add("Dev Team");

        String result = command.execute(parameters);

        Assertions.assertEquals(String.format(CreateNewTeamCommand.TEAM_CREATE_MESSAGE, "Dev Team"), result);
    }

    @Test
    public void should_ThrowException_WithInvalidNumberOfArguments() {
        SystemRepository systemRepository = new SystemRepositoryImpl();
        CreateNewTeamCommand command = new CreateNewTeamCommand(systemRepository);

        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            command.execute(parameters);
        });
    }

    @Test(expected = InvalidUserInputException.class)
    public void should_ThrowException_When_TeamIsPartOfTeam()  {
        SystemRepository repository = new SystemRepositoryImpl();
        CreateNewTeamCommand command = new CreateNewTeamCommand(repository);

        Team team = new TeamImpl("Dev Team");
        repository.createTeam(team.getName());

        List<String> parameters = new ArrayList<>();
        parameters.add("Rak");
        command.execute(parameters);

    }
}
