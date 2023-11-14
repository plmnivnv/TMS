package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class CreateNewTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TEAM_CREATE_MESSAGE = "Team with name %s was created";
    SystemRepository systemRepository;

    public CreateNewTeamCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createTeam(name);
    }

    private String createTeam(String name){
        Team team = systemRepository.createTeam(name);

        return String.format(TEAM_CREATE_MESSAGE, name);
    }
}
