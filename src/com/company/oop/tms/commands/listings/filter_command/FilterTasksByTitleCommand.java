package com.company.oop.tms.commands.listings.filter_command;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByTitleCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String NO_SUCH_TITLE_MSG = "There is no Task with such title!";

    private SystemRepository systemRepository;
    private List<Task> taskList;

    public FilterTasksByTitleCommand(SystemRepository systemRepository) {
        taskList = systemRepository.getTaskList();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String targetTitle = parameters.get(0);
        return filterTasksByTitle(targetTitle);
    }

    private String filterTasksByTitle(String targetTitle) {
        StringBuilder result = new StringBuilder();
        taskList.stream()
                .filter(task -> task.getTitle().contains(targetTitle))
                .forEach(task -> {
                    result.append(task).append(System.lineSeparator())
                            .append("----------")
                            .append(System.lineSeparator());
                });
        if (result.isEmpty()){
            System.out.println(NO_SUCH_TITLE_MSG);
        }
        return result.toString().trim();

    }
}
