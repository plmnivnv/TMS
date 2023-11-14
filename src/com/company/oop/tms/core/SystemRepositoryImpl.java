package com.company.oop.tms.core;

import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.*;
import com.company.oop.tms.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class SystemRepositoryImpl implements SystemRepository {

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
    public List<Member> getMemberList() {
        return new ArrayList<>(memberList);
    }
    @Override
    public List<Team> getTeamList() {
        return new ArrayList<>(teamList);
    }
    @Override
    public List<Board> getBoardList() {
        return new ArrayList<>(boardList);
    }
    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

    @Override
    public Member createMember(String name) {
        Member member = new MemberImpl(name);
        if (memberList.isEmpty()){
            memberList.add(member);
            return member;
        } else {
           if(!findExistingName(memberList, name)){
               memberList.add(member);
               return member;
           }
           throw new IllegalArgumentException(String.format(NAME_ALREADY_EXIST, name));
        }
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        if (teamList.isEmpty()){
            teamList.add(team);
            return team;
        } else {
            if(!findExistingName(teamList, name)){
                teamList.add(team);
                return team;
            }
            throw new IllegalArgumentException(String.format(NAME_ALREADY_EXIST, name));
        }
    }

    @Override
    public Board createBoard(String name) {
        return null;
    }

    @Override
    public Comment createComment(String author, String content) {
        return null;
    }

    private  <T extends Nameable> boolean findExistingName(List<T> elements, String name){
        boolean isFound = false;
        for (T element: elements) {
            if(element.getName().equals(name)){
                isFound = true;
            }
        }
        return isFound;
//        throw new IllegalArgumentException(String.format(NAME_ALREADY_EXIST, name));
    }
}
