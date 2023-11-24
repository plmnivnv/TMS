package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FilterAssigneeTasksByStatusCommand implements Command {


    private static final int STATUS_INDEX = 0;
    private static final int EXPECTED_ARGUMENTS = 1;
    public static final String NO_RESULTS_MESSAGE = "No assigned task with matching status!";
    public static final String END_MESSAGE = "----- END -----";

    List<Bug> bugs;
    List<Story> stories;
    public FilterAssigneeTasksByStatusCommand(SystemRepository systemRepository){
        bugs = systemRepository.getBugList();
        stories = systemRepository.getStoryList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS);

        String searchedStatus = parameters.get(STATUS_INDEX).toLowerCase();


        Stream<Bug> streamBug = bugs.stream()
                .filter(bug -> bug.getStatusBug().toString().toLowerCase().contains(searchedStatus));


        Stream<Story> streamStory = stories.stream()
                .filter(story -> story.getStatus().toString().toLowerCase().contains(searchedStatus));


        if (streamBug.findAny().isEmpty() && streamStory.findAny().isEmpty())
            return NO_RESULTS_MESSAGE;

        bugs.stream()
                .filter(bug -> bug.getStatusBug().toString().toLowerCase().contains(searchedStatus)).sorted(Comparator.comparing(Bug::getTitle))
                .forEach(bug -> System.out.println(bug + "\n"));


        stories.stream()
                .filter(story -> story.getStatus().toString().toLowerCase().contains(searchedStatus)).sorted(Comparator.comparing(Story::getTitle))
                .forEach(story -> System.out.println(story +"\n"));
        return END_MESSAGE;

    }

}
