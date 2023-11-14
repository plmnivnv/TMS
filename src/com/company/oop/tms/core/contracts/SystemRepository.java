package com.company.oop.tms.core.contracts;

import com.company.oop.tms.models.contracts.*;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.Size;

import java.util.List;

public interface SystemRepository {


    List<Member> getMemberList();
    List<Team> getTeamList();
    List<Board> getBoardList();
    List<Task> getTaskList();
    Bug createBug(String title, String description, List<String> stepsToProduce, Priority priority, Severity severity,Member assignee);
    Story createStory(String title, String description, Priority priority, Size size, Member assignee);
    Feedback createFeedback(String title, String description, int rating);
    <T extends Nameable> T findElementByName(List<T> list, String name,String type);
    Member createMember(String name);
    Team createTeam(String name);
    Board createBoard(String name);
    Comment createComment(String author, String description);
}
