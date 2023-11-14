package com.company.oop.tms.core.contracts;

import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.List;

public interface SystemRepository {


    List<Member> getMemberList();
    List<Team> getTeamList();
    List<Board> getBoardList();
    List<Task> getTaskList();

    Member createMember(String name);
    Team createTeam(String name);
    Board createBoard(String name);
    Comment createComment(String author, String description);
}
