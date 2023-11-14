package com.company.oop.tms.core;

import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.*;
import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class SystemRepositoryImpl implements SystemRepository {

    public static final String MEMBER_WITH_NAME_S_ALREADY_EXISTS = "Member with name %s already exists!";
    public static final String NAME_ALREADY_EXIST = "%s name already exist";
    private int nextId;

    private final List<Member> memberList = new ArrayList<>();
    private final List<Team> teamList = new ArrayList<>();
    private final List<Board> boardList = new ArrayList<>();
    private final List<Task> taskList = new ArrayList<>();


    public SystemRepositoryImpl() {
        nextId = 0;
    }

    @Override
    public Member createMember(String name) {
        Member member = new MemberImpl(name);
        if (memberList.isEmpty()){
            memberList.add(member);
            return member;
        } else {
            findExistingName(memberList, name);
            memberList.add(member);
            return member;
        }
    }

    @Override
    public Team createTeam(String name) {
        for (Team element : teamList) {
            if (!element.getName().equals(name)){
                Team team = new TeamImpl(name);
                teamList.add(team);
                return team;
            }
        }
        throw new IllegalArgumentException(String.format(MEMBER_WITH_NAME_S_ALREADY_EXISTS, name));
    }

    @Override
    public Board createBoard(String name) {
        return null;
    }

    @Override
    public Comment createComment(String author, String content) {
        return null;
    }

    private  <T extends Nameable> T findExistingName(List<T> elements, String name){
        boolean isFound = false;
        for (T element: elements) {
            if(!element.getName().equals(name)){
                return element;
            }
        }
        throw new IllegalArgumentException(String.format(NAME_ALREADY_EXIST, name));
    }
}
