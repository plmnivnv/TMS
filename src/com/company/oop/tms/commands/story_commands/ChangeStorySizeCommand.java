package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class ChangeStorySizeCommand implements Command {


    public static final String SIZE_CHANGE_MESSAGE = "Size of Story with ID %d changed to %s.";
    SystemRepository systemRepository;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStorySizeCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = Integer.parseInt(parameters.get(0));
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class);
        return changeStorySize(id, size);
    }

    private String changeStorySize(int id, Size size) {
        Story story = systemRepository.findElementById(systemRepository.getStoryList(), id, "Story");
        story.changeSize(size);
        return String.format(SIZE_CHANGE_MESSAGE, story.getId(), story.getSize());
    }


}
