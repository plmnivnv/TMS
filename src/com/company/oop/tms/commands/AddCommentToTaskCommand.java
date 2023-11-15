package com.company.oop.tms.commands;

import com.company.oop.tms.commands.contracts.Command;
import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToTaskCommand implements Command {

    public static final String COMMENT_ADDED_MESSAGE = "Comment from %s added to task with ID: %d";
    SystemRepository systemRepository;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;


    public AddCommentToTaskCommand(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        Member author = systemRepository.findElementByName(systemRepository.getMemberList(), parameters.get(0), "Author");
        String content = parameters.get(1);
        int id = Integer.parseInt(parameters.get(2));
        return AddCommentToTask(author, content, id);
    }

    private String AddCommentToTask(Member author,String content, int id){
        Task task = systemRepository.findElementById(systemRepository.getTaskList(), id, "Task");
        Comment comment = systemRepository.createComment(author, content);
        task.addComment(comment);
      return String.format(COMMENT_ADDED_MESSAGE,author.getName(),id);
    }
}
