package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.StatusBugAndStory;

import java.util.List;

public class FilterAssigneeTasksByStatusCommand implements Command {

    List<Bug> bugList;
    List<Story> storyList;
    List<Task> taskList;



    public FilterAssigneeTasksByStatusCommand(SystemRepository systemRepository) {
        bugList = systemRepository.getBugList();
        storyList = systemRepository.getStoryList();
        taskList = systemRepository.getTaskList();
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }


    private String filterAssigneeTasksByStatus(StatusBugAndStory targetStatus){
        return null;
    }


}
