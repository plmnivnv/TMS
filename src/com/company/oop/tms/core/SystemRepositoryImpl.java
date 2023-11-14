package com.company.oop.tms.core;

import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.BoardImpl;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.*;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.FeedbackImpl;
import com.company.oop.tms.models.tasks.StoryImpl;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class SystemRepositoryImpl implements SystemRepository {

    public static final String NAME_ALREADY_EXIST = "%s with name %s already exist";
    public static final String MEMBER = "Member";
    public static final String TEAM = "Team";
    public static final String BOARD = "Board";
    public static final String NOT_EXISTING_ELEMENT = "%s with name %s not exist!";
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
    public Bug createBug(String title, String description, List<String> stepsToProduce, Priority priority, Severity severity, Member assignee) {
        Bug bug = new BugImpl(++nextId, title, description, stepsToProduce, priority, severity, assignee);
        taskList.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, Member assignee) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, assignee);
        taskList.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        taskList.add(feedback);
        return feedback;
    }

    @Override
    public <T extends Nameable> T findElementByName(List<T> list, String name, String type) {
        for (T element : list) {
            if(element.getName().equalsIgnoreCase(name)){
                return element;
            }
        }
        throw new NoSuchElementException(String.format(NOT_EXISTING_ELEMENT,type, name));
    }

    @Override
    public Member createMember(String name) {
        Member member = new MemberImpl(name);
        if (memberList.isEmpty()) {
            memberList.add(member);
            return member;
        } else {
            if (!findExistingName(memberList, name)) {
                memberList.add(member);
                return member;
            }
            throw new NoSuchElementException(String.format(NAME_ALREADY_EXIST, MEMBER, name));
        }
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        if (teamList.isEmpty()) {
            teamList.add(team);
            return team;
        } else {
            if (!findExistingName(teamList, name)) {
                teamList.add(team);
                return team;
            }
            throw new NoSuchElementException(String.format(NAME_ALREADY_EXIST,TEAM, name));
        }
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        if (boardList.isEmpty()) {
            boardList.add(board);
            return board;
        } else {
            if (!findExistingName(boardList, name)) {
                boardList.add(board);
                return board;
            }
            throw new NoSuchElementException(String.format(NAME_ALREADY_EXIST,BOARD, name));
        }
    }


    @Override
    public Comment createComment(String author, String content) {
        return null;
    }

    private <T extends Nameable> boolean findExistingName(List<T> elements, String name) {
        boolean isFound = false;
        for (T element : elements) {
            if (element.getName().equals(name)) {
                isFound = true;
            }
        }
        return isFound;
    }
}
