package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.ActivityHistoryImpl;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamActivityCommand implements Command {

    //TODO

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    SystemRepository systemRepository;

    public ShowAllTeamActivityCommand(SystemRepository systemRepository){

        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Team teamName = systemRepository.findElementByName(systemRepository.getTeamList(), parameters.get(0), "Team");
        return showAllTeamActivity(teamName);
    }


    private String showAllTeamActivity(Team teamName){
        Team team = systemRepository.findElementByName(systemRepository.getTeamList(), teamName.getName(), "Team");
        List<Member> memberList = team.getMembers();
        StringBuilder result = new StringBuilder();
        result.append("TEAM: ").append(team.getName().toUpperCase()).append(System.lineSeparator());
        for (Member member : memberList) {
         result.append("*** ").append(member.getName()).append(" ***").append(System.lineSeparator()).append("----------").append(System.lineSeparator());
            for (ActivityHistoryImpl element: member.getActivityHistoryList()) {
                result.append(element).append(System.lineSeparator());
            }
            result.append("----------").append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
