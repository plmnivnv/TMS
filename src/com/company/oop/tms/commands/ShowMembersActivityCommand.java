package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.ActivityHistoryImpl;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowMembersActivityCommand implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    SystemRepository systemRepository;

    public ShowMembersActivityCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Member member = systemRepository.findElementByName(systemRepository.getMemberList(), parameters.get(0), "Member");
        return showMemberActivity(member);
    }


    private String showMemberActivity(Member memberName){
        Member member = systemRepository.findElementByName(systemRepository.getMemberList(), memberName.getName(), "Member");
        List<ActivityHistoryImpl> activityList = member.getActivityHistoryList();
        StringBuilder result = new StringBuilder();
        for (ActivityHistoryImpl activityHistory : activityList) {
            result.append(activityHistory).append(System.lineSeparator());
        }
        return result.toString().trim();
    }


}
