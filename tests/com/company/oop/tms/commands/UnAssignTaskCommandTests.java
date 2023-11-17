package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.SystemRepositoryImpl;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnAssignTaskCommandTests {
    private SystemRepository systemRepository;
    private List<String> args;

    @BeforeEach
    public void createRepo() {
        systemRepository = new SystemRepositoryImpl();
        args = new ArrayList<>();

    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Command unassignCommand = new UnAssignTaskCommand(systemRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unassignCommand.execute(new ArrayList<>());
        });
    }
    @Test
    public void execute_Should_UnAssignTask_When_ValidParameters() {
        Command unassignCommand = new UnAssignTaskCommand(systemRepository);
        Member member = systemRepository.createMember("Member");
        Task task = systemRepository.createBug("TitleOfTask",
                "Description"
                ,args
                , Priority.LOW
                , Severity.MINOR
                ,member);
        int firstParam = 1;
        args.add(String.valueOf(firstParam));
        args.add(member.getName());

        Assertions.assertDoesNotThrow(() -> unassignCommand.execute(args));
    }
}
