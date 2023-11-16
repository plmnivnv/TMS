package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class CreateMemberCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String MEMBER_CREATE_MESSAGE = "Member with name %s was created";
    SystemRepository systemRepository;

    public CreateMemberCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createMember(name);
    }

    private String createMember(String name){
        Member member = systemRepository.createMember(name);
        return String.format(MEMBER_CREATE_MESSAGE, name);
    }


}
