package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamActivityCommandTests {
    private final int VALID_TEAM_NAME_LENGTH = 5;
    private final int VALID_MEMBER_NAME_LENGTH = 10;
    private final String VALID_TEAM_NAME = TestUtils.getString(VALID_TEAM_NAME_LENGTH + 1);
    private final String VALID_MEMBER_NAME = TestUtils.getString(VALID_TEAM_NAME_LENGTH - 1);
    private SystemRepository systemRepository;
    private List<String> args;

    @BeforeEach
    public void createRepo() {
        systemRepository = new SystemRepositoryImpl();
        args = new ArrayList<>();
        args.add(VALID_TEAM_NAME);
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Command showTeamActivity = new ShowAllTeamActivityCommand(systemRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showTeamActivity.execute(new ArrayList<>());
        });
    }

    @Test
    public void execute_Should_ShowAllTeamActivity_When_ValidParameters() {
        Command showTeamActivity = new ShowAllTeamActivityCommand(systemRepository);
        Team team = systemRepository.createTeam(VALID_TEAM_NAME);


        Assertions.assertDoesNotThrow(() -> showTeamActivity.execute(args));
    }


}
