package com.company.oop.tms.commands.listings.SortCommand;

import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.TasksImpl;
import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortByTitleCommand {

    private  List<Task> taskList;

    public SortByTitleCommand(SystemRepository systemRepository) {
        taskList = systemRepository.getTaskList();
    }








}
