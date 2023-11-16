package com.company.oop.tms.models;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class TeamImplTests {

    private static final int NAME_MIN_LENGTH = 5;

    public String VALID_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH + 1);
    public String INVALID_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH - 1);

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        TeamImpl team = new TeamImpl(VALID_NAME_LENGTH);

        Assertions.assertEquals(VALID_NAME_LENGTH, team.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new TeamImpl(INVALID_NAME_LENGTH));
    }

    @Test
    public void getName_Should_Return_Name() {
        TeamImpl team = new TeamImpl(VALID_NAME_LENGTH);

        Assertions.assertEquals(VALID_NAME_LENGTH, team.getName());
    }

    @Test
    public void getMembers_Should_Return_Copy_Of_List() {
        TeamImpl team = new TeamImpl(VALID_NAME_LENGTH);
        List<Member> activityMemberReference = team.getMembers();
        List<Member> sameReference = team.getMembers();


        Assertions.assertEquals(activityMemberReference, sameReference);
    }

    @Test
    public void getBoards_Should_Return_Copy_Of_List() {
        TeamImpl team = new TeamImpl(VALID_NAME_LENGTH);
        List<Board> activityMemberReference = team.getBoards();
        List<Board> sameReference = team.getBoards();


        Assertions.assertEquals(activityMemberReference, sameReference);
    }

    @Test
    public void addMember_Should_AddMembers_When_Member_Is_Added() {
        TeamImpl team = new TeamImpl(VALID_NAME_LENGTH);
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);

        team.addMember(member);

        Assertions.assertEquals(1, team.getMembers().size());
    }
    @Test
    public void addBoard_Should_AddBoard_When_Board_Is_Added() {
        TeamImpl team = new TeamImpl(VALID_NAME_LENGTH);
        Board board = new BoardImpl("Board");

        team.addBoard(board);

        Assertions.assertEquals(1, team.getBoards().size());
    }

}
