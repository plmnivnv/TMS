package com.company.oop.tms.core;

import com.company.oop.tms.core.contracts.SystemRepository;
import com.company.oop.tms.exceptions.NoSuchElementException;
import com.company.oop.tms.models.CommentImpl;
import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.TeamImpl;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.contracts.Team;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.contracts.Story;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class SystemRepositoryImplTests {

    public static final String VALID_MEMBER_NAME = "Gosho";
    public static final String VALID_DESCRIPTION = "Descriptiondescritpion";
    public static final String VALID_BUG_NAME = "Titletitletitle";
    public static final String VALID_TEAM_NAME = "TeamName";
    public static final String VALID_BOARD_NAME = "BoardName";
    public static final String VALID_CONTENT = "somevalidcontent";
    SystemRepository systemRepository;


    @BeforeEach
    public void setup() {
        systemRepository = new SystemRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeCollections(){
        //Arrange,Act,Assert
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(systemRepository.getMemberList()),
                ()-> Assertions.assertNotNull(systemRepository.getTaskList()),
                ()-> Assertions.assertNotNull(systemRepository.getBoardList()),
                ()-> Assertions.assertNotNull(systemRepository.getStoryList()),
                ()-> Assertions.assertNotNull(systemRepository.getTeamList()),
                ()-> Assertions.assertNotNull(systemRepository.getBugList()),
                ()-> Assertions.assertNotNull(systemRepository.getFeedbackList())
        );
    }

    @Test
    public void getMemberList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Member> categoriesReference = systemRepository.getMemberList();
        List<Member> sameReference = systemRepository.getMemberList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getTeamList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Team> categoriesReference = systemRepository.getTeamList();
        List<Team> sameReference = systemRepository.getTeamList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getBoardList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Board> categoriesReference = systemRepository.getBoardList();
        List<Board> sameReference = systemRepository.getBoardList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getTaskList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Task> categoriesReference = systemRepository.getTaskList();
        List<Task> sameReference = systemRepository.getTaskList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getBugList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Bug> categoriesReference = systemRepository.getBugList();
        List<Bug> sameReference = systemRepository.getBugList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getStoryList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Story> categoriesReference = systemRepository.getStoryList();
        List<Story> sameReference = systemRepository.getStoryList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getFeedbackList_Should_ReturnCopyOfCollection(){
        //Arrange,Act
        List<Feedback> categoriesReference = systemRepository.getFeedbackList();
        List<Feedback> sameReference = systemRepository.getFeedbackList();
        //Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void createBug_Should_AddBugToList(){
        //Arrange,Act
        List<String> steps = new ArrayList<>();
        systemRepository.createBug(
                VALID_BUG_NAME,
                VALID_DESCRIPTION,
                steps,
                Priority.HIGH,
                Severity.MAJOR,
                systemRepository.createMember(VALID_MEMBER_NAME));
        //Assert
        Assertions.assertEquals(1, systemRepository.getBugList().size());
        Assertions.assertEquals(1, systemRepository.getTaskList().size());
    }

    @Test
    public void createStory_Should_AddStoryToLists(){
        //Arrange,Act
        systemRepository.createStory(
                VALID_BUG_NAME,
                VALID_DESCRIPTION,
                Priority.HIGH,
                Size.LARGE,
                systemRepository.createMember(VALID_MEMBER_NAME));
        //Assert
        Assertions.assertEquals(1, systemRepository.getTaskList().size());
        Assertions.assertEquals(1, systemRepository.getStoryList().size());
    }

    @Test
    public void createFeedback_Should_AddFeedbackToLists(){
        //Arrange,Act
        systemRepository.createFeedback(
                VALID_BUG_NAME,
                VALID_DESCRIPTION,
                5);
        //Assert
        Assertions.assertEquals(1, systemRepository.getTaskList().size());
        Assertions.assertEquals(1, systemRepository.getFeedbackList().size());
    }

    @Test
    public void createMember_Should_AddMemberToList(){
        //Arrange,Act
        systemRepository.createMember(VALID_MEMBER_NAME);
        //Assert
        Assertions.assertEquals(1, systemRepository.getMemberList().size());
    }

    @Test
    public void createMember_Should_ThrowException_When_NameIsNotUnique(){
        //Arrange,Act
        systemRepository.createBoard(VALID_MEMBER_NAME);
        //Assert
        Assertions.assertThrows(NoSuchElementException.class, ()-> systemRepository.createMember(VALID_MEMBER_NAME));
    }

    @Test
    public void createTeam_Should_AddTeamToList(){
        //Arrange,Act
        systemRepository.createTeam(VALID_TEAM_NAME);
        //Assert
        Assertions.assertEquals(1, systemRepository.getTeamList().size());
    }

    @Test
    public void createTeam_Should_ReturnTeam_When_InputIsValid(){
        //Arrange,Act
        Team team = new TeamImpl(VALID_TEAM_NAME);
        //Assert
        Assertions.assertEquals(team.getName(), VALID_TEAM_NAME);
    }

    @Test
    public void createTeam_Should_ThrowException_When_NameIsNotUnique(){
        //Arrange,Act
        systemRepository.createBoard(VALID_TEAM_NAME);
        //Assert
        Assertions.assertThrows(NoSuchElementException.class, ()-> systemRepository.createTeam(VALID_TEAM_NAME));
    }

    @Test
    public void createBoard_Should_AddBoardToList(){
        //Arrange,Act
        systemRepository.createBoard(VALID_BOARD_NAME);
        //Assert
        Assertions.assertEquals(1, systemRepository.getBoardList().size());
    }
    @Test
    public void createBoard_Should_ThrowException_When_NameIsNotUnique(){
        //Arrange,Act
        systemRepository.createTeam(VALID_BOARD_NAME);
        //Assert
        Assertions.assertThrows(NoSuchElementException.class, ()-> systemRepository.createBoard(VALID_BOARD_NAME));
    }

    @Test
    public void createComment_Should_ReturnComment_When_InputIsValid(){
        //Arrange,Act
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Comment comment = systemRepository.createComment(member, VALID_CONTENT);;
        //Assert
        Assertions.assertAll(
                ()-> Assertions.assertEquals(comment.getAuthor().getName(), VALID_MEMBER_NAME),
                ()-> Assertions.assertEquals(comment.getContent(), VALID_CONTENT)
        );
    }

    @Test
    public void findElementByName_Should_ReturnElement_When_NameIsValid(){
        //Arrange
        Board board = systemRepository.createBoard(VALID_BOARD_NAME);
        //Act
        Board foundBoard = systemRepository.findElementByName(systemRepository.getBoardList(), board.getName(), "Board");
        //Assert
        Assertions.assertEquals(board.getName(), foundBoard.getName());
    }

    @Test
    public void findElementByName_Should_ThrowException_When_NameNotFound(){
        //Arrange,Act,Assert
        Assertions.assertThrows(NoSuchElementException.class, ()-> systemRepository.findElementByName(systemRepository.getBoardList(), VALID_BOARD_NAME, "Board"));
    }

    @Test
    public void findElementById_Should_ReturnElement_When_IdIsValid(){
        //Arrange
        List<String> steps = new ArrayList<>();
        Bug bug = systemRepository.createBug(VALID_BUG_NAME,
                VALID_DESCRIPTION,
                steps,
                Priority.HIGH,
                Severity.MAJOR,
                systemRepository.createMember(VALID_MEMBER_NAME));
        //Act
        Bug bugFound = systemRepository.findElementById(systemRepository.getBugList(), bug.getId(), "Bug");
        //Assert
        Assertions.assertAll(
                ()-> Assertions.assertEquals(bug.getId(), bugFound.getId()),
                ()-> Assertions.assertEquals(bug.getStatusBug(), bugFound.getStatusBug()),
                ()-> Assertions.assertEquals(bug.getPriority(), bugFound.getPriority()),
                ()-> Assertions.assertEquals(bug.getSeverity(), bugFound.getSeverity()),
                ()-> Assertions.assertEquals(bug.getAssignee(), bugFound.getAssignee()),
                ()-> Assertions.assertEquals(bug.getTitle(), bugFound.getTitle()),
                ()->Assertions.assertEquals(bug.getDescription(), bugFound.getDescription())
        );
    }

    @Test
    public void findElementById_Should_ThrowException_When_IdNotFound(){
        //Arrange,Act,Assert
        Assertions.assertThrows(NoSuchElementException.class, ()-> systemRepository.findElementById(systemRepository.getBugList(), 1, "Bug"));
    }






}
