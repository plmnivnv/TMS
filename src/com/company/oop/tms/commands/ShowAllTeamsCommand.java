package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    SystemRepository systemRepository;

    public ShowAllTeamsCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showAllTeams();
    }

    private String showAllTeams(){
        List<Team> teams = systemRepository.getTeamList();
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Team team : teams) {
            result.append(index).append(".").append(team.getName()).append(System.lineSeparator());
            index++;
        }
        return result.toString().trim();
    }

}
