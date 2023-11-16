package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERROR = String.format("Team's name must be between %d and %d symbols",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    private String name;
    private List<Member> memberList = new ArrayList<>();
    private List<Board> boardList = new ArrayList<>();


    public TeamImpl(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name){
        ValidationHelpers.validateStringLength(name,
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);
        this.name = name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(memberList);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boardList);
    }

    @Override
    public void addMember(Member member) {
        memberList.add(member);
    }

    @Override
    public void addBoard(Board board) {
        boardList.add(board);
    }

}
