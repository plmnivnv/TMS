package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.StatusStory;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryStatusCommand implements Command {

    public static final String STATUS_CHANGE_MESSAGE = "Status of Story with ID %d changed to %s.";
    SystemRepository systemRepository;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStoryStatusCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        StatusStory statusStory = ParsingHelpers.tryParseEnum(parameters.get(1), StatusStory.class);
        return changeStoryStats(id, statusStory);
    }

    private String changeStoryStats(int id, StatusStory statusStory) {
        Story story = systemRepository.findElementById(systemRepository.getStoryList(), id, "Story");
        story.changeStatusStory(statusStory);
        return String.format(STATUS_CHANGE_MESSAGE, story.getId(), story.getStatus());
    }
}
