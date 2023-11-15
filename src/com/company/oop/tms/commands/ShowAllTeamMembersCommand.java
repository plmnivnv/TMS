package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.CommandFactory;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand implements Command {
    public static final String NO_MEMBER_MESSAGE = "No members in system!";
    SystemRepository systemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public ShowAllTeamMembersCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        Team team = systemRepository.findElementByName(systemRepository.getTeamList(), parameters.get(0), "Team");
        return showTeamMembers(team);
    }

    private String showTeamMembers(Team team){
        Team teamName = systemRepository.findElementByName(systemRepository.getTeamList(), team.getName(), "Team");
        List<Member> memberList = teamName.getMembers();
        StringBuilder result = new StringBuilder();
        int index = 1;
        if (memberList.isEmpty()) {
            result.append(NO_MEMBER_MESSAGE);
        } else {
            for (Member member : memberList) {
                result.append(index).append(".").append(member.getName()).append(System.lineSeparator());
                index++;
            }
        }
        return result.toString().trim();
    }
}
