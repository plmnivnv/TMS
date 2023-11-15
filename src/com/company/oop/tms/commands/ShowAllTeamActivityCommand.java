package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamActivityCommand implements Command {

    //TODO

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    SystemRepository systemRepository;

    public ShowAllTeamActivityCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showAllTeamActivity();
    }

    private String showAllTeamActivity(){
        List<Member> memberList = systemRepository.getMemberList();
        StringBuilder result = new StringBuilder();
        for (Member member : memberList) {
            result.append(member.getActivityHistoryList().toString());
        }
        return result.toString();
    }
}
