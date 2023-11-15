package com.company.oop.tms.commands.story_commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.utils.ParsingHelpers;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateStoryInBoardCommand implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public static final String STORY_WITH_NAME_S_ADDED_TO_BOARD_S = "Story with ID %d created in board %s";
    SystemRepository systemRepository;

    public CreateStoryInBoardCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(3), Size.class);
        Member assignee = systemRepository.findElementByName(systemRepository.getMemberList(),parameters.get(4), "Member");
        String boardName = parameters.get(5);
        return createStoryInBoard(title,description,priority, size, assignee,boardName);
    }


    private String createStoryInBoard(String title, String description, Priority priority, Size size,Member assignee, String boardName){
        Board board = systemRepository.findElementByName(systemRepository.getBoardList(), boardName, "Board");
        Story story = systemRepository.createStory(title, description, priority, size, assignee);
        board.addTask(story);
        return String.format(STORY_WITH_NAME_S_ADDED_TO_BOARD_S, story.getId(), board.getName());
    }



}
