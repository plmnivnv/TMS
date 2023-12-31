package com.company.oop.tms.core;

import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.BoardImpl;
import com.company.oop.tms.models.CommentImpl;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.*;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.FeedbackImpl;
import com.company.oop.tms.models.tasks.StoryImpl;
import com.company.oop.tms.models.tasks.contracts.*;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class SystemRepositoryImpl implements SystemRepository {

    public static final String NAME_ALREADY_EXIST = "Name %s already exist. Choose another name!";
    public static final String NOT_EXISTING_ELEMENT = "%s with name %s not exist!";
    public static final String NOT_EXISTING_TASK = "%s with ID %d not exist!";
    private int nextId;

    private final List<Member> memberList = new ArrayList<>();
    private final List<Team> teamList = new ArrayList<>();
    private final List<Board> boardList = new ArrayList<>();
    private final List<Task> taskList = new ArrayList<>();
    private final List<Bug> bugList = new ArrayList<>();
    private final List<Story> storyList = new ArrayList<>();
    private final List<Feedback> feedbackList = new ArrayList<>();


    public SystemRepositoryImpl() {
        nextId = 1;
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
    public List<Bug> getBugList() {
        return new ArrayList<>(bugList);
    }
    @Override
    public List<Story> getStoryList() {
        return new ArrayList<>(storyList);
    }
    @Override
    public List<Feedback> getFeedbackList() {
        return new ArrayList<>(feedbackList);
    }

    @Override
    public Bug createBug(String title, String description, List<String> stepsToProduce, Priority priority, Severity severity, Member assignee) {
        Bug bug = new BugImpl(nextId, title, description, stepsToProduce, priority, severity, assignee);
        ++nextId;
        taskList.add(bug);
        bugList.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, Member assignee) {
        Story story = new StoryImpl(nextId, title, description, priority, size, assignee);
        ++nextId;
        taskList.add(story);
        storyList.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(nextId, title, description, rating);
        ++nextId;
        taskList.add(feedback);
        feedbackList.add(feedback);
        return feedback;
    }

    @Override
    public <T extends Nameable> T findElementByName(List<T> list, String name, String type) {
        for (T element : list) {
            if (element.getName().equalsIgnoreCase(name)) {
                return element;
            }
        }
        throw new NoSuchElementException(String.format(NOT_EXISTING_ELEMENT, type, name));
    }


    @Override
    public <T extends Identifiable> T findElementById(List<T> list, int id, String type) {
        for (T element : list) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new NoSuchElementException(String.format(NOT_EXISTING_TASK, type, id));
    }

    @Override
    public Member createMember(String name) {
        Member member = new MemberImpl(name);
        if (memberList.isEmpty() && teamList.isEmpty() && boardList.isEmpty()) {
            memberList.add(member);
            return member;
        } else {
            if (!findExistingName(teamList, name) && !findExistingName(boardList, name) && !findExistingName(memberList, name)) {
                memberList.add(member);
                return member;
            }
            throw new NoSuchElementException(String.format(NAME_ALREADY_EXIST, name));
        }
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        if (memberList.isEmpty() && teamList.isEmpty() && boardList.isEmpty()) {
            teamList.add(team);
            return team;
        } else {
            if (!findExistingName(teamList, name) && !findExistingName(boardList, name) && !findExistingName(memberList, name)) {
                teamList.add(team);
                return team;
            }
            throw new NoSuchElementException(String.format(NAME_ALREADY_EXIST, name));
        }
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        if (boardList.isEmpty() && teamList.isEmpty()) {
            boardList.add(board);
            return board;
        } else {
            if (!findExistingName(teamList, name)) {
                boardList.add(board);
                return board;
            }
            throw new NoSuchElementException(String.format(NAME_ALREADY_EXIST, name));
        }
    }


    @Override
    public Comment createComment(Member author, String content) {
        return new CommentImpl(author, content);
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
