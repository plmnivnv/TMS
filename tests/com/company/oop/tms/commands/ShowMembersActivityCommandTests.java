package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ShowMembersActivityCommandTests {
    private final int VALID_MEMBER_NAME_LENGTH = 5;
    private final String VALID_MEMBER_NAME = TestUtils.getString(VALID_MEMBER_NAME_LENGTH + 1);


    private SystemRepository systemRepository;
    private List<String> args;

    @BeforeEach
    public void createRepo() {
        systemRepository = new SystemRepositoryImpl();
        args = new ArrayList<>();
        args.add(VALID_MEMBER_NAME);
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Command showMemberActivity = new ShowMembersActivityCommand(systemRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showMemberActivity.execute(new ArrayList<>());
        });
    }
    @Test
    public void execute_Should_ShowMemberActivity_When_ValidParameters() {
        Command showMemberActivity = new ShowMembersActivityCommand (systemRepository);
        Member member = systemRepository.createMember(VALID_MEMBER_NAME);


        Assertions.assertDoesNotThrow(() -> showMemberActivity.execute(args));
    }
}
