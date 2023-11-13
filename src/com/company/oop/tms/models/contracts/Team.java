package com.company.oop.tms.models.contracts;

import java.util.List;

public interface Team extends Nameable {

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void addBoard(Board board);

}
