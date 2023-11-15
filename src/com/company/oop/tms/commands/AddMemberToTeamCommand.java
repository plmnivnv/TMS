package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class AddMemberToTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String MEMBER_ADDED_TO_TEAM_MESSAGE = "Member with name %s added to Team: %s";

    SystemRepository systemRepository;

    public AddMemberToTeamCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        Member member = systemRepository.findElementByName(systemRepository.getMemberList(), parameters.get(0), "Member");
        Team team = systemRepository.findElementByName(systemRepository.getTeamList(), parameters.get(1), "Team");
        return AddMemberToTeam(member, team);
    }

    private String AddMemberToTeam(Member memberName,Team teamName){
        Member member = systemRepository.findElementByName(systemRepository.getMemberList(), memberName.getName(),"Member");
        Team team = systemRepository.findElementByName(systemRepository.getTeamList(), teamName.getName(), "Team");
        team.addMember(member);
        return String.format(MEMBER_ADDED_TO_TEAM_MESSAGE, member.getName(), team.getName());
    }
}
