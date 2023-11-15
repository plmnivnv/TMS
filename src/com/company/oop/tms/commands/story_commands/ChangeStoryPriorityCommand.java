package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryPriorityCommand implements Command {

    public static final String PRIORITY_CHANGE_MESSAGE = "Priority of Story with ID %d changed to %s.";
    SystemRepository systemRepository;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStoryPriorityCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        return changeStoryPriority(id, priority);
    }

    private String changeStoryPriority(int id, Priority priority) {
        Story story = systemRepository.findElementById(systemRepository.getStoryList(), id, "Story");
        story.changePriority(priority);
        return String.format(PRIORITY_CHANGE_MESSAGE, story.getId(), story.getPriority());
    }
}
