package com.company.oop.tms.core.contracts;

import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;

public interface SystemRepository {


    Member createMember(String name);
    Team createTeam(String name);
    Board createBoard(String name);
    Comment createComment(String author, String description);
}
