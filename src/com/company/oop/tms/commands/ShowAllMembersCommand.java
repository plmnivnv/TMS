package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ShowAllMembersCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    SystemRepository systemRepository;

    public ShowAllMembersCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showMembers();
    }

    private String showMembers(){
        List<Member> members = systemRepository.getMemberList();
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Member member : members) {
            result.append(index).append(".").append(member.getName()).append(System.lineSeparator());
            index++;
        }
        return result.toString().trim();
    }


}
