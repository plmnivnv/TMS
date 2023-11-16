package com.company.oop.tms.models;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.contracts.Board;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.TasksImpl;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class BoardImplTests {
    private static final int NAME_MIN_LENGTH = 5;
    public String VALID_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH + 1);
    public String INVALID_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH - 1);

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        BoardImpl board = new BoardImpl(VALID_NAME_LENGTH);

        Assertions.assertEquals(VALID_NAME_LENGTH, board.getName());
    }
    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new BoardImpl(INVALID_NAME_LENGTH));
    }
    @Test
    public void getTask_Should_Return_Copy_Of_List() {
        BoardImpl board = new BoardImpl(VALID_NAME_LENGTH);
        List<Task> activityMemberReference = board.getTask();
        List<Task> sameReference = board.getTask();


        Assertions.assertEquals(activityMemberReference, sameReference);
    }
    @Test
    public void addTask_Should_AddTask_When_Task_Is_Added() {
        List<String> testStep = new ArrayList<>();
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        BoardImpl board = new BoardImpl(VALID_NAME_LENGTH);
        Task task = new BugImpl(1, "TestBugTitle", "Description", testStep, Priority.LOW, Severity.MINOR, member);

        board.addTask(task);

        Assertions.assertEquals(1, board.getTask().size());
    }
    @Test
    public void removeTask_Should_RemoveTask_When_Task_Is_Removed() {
        List<String> testStep = new ArrayList<>();
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        BoardImpl board = new BoardImpl(VALID_NAME_LENGTH);
        Task task = new BugImpl(1, "TestBugTitle", "Description", testStep, Priority.LOW, Severity.MINOR, member);

        board.addTask(task);
        board.removeTask(task);

        Assertions.assertEquals(0, board.getTask().size());
    }

    @Test
    public void logActivityHistory_Should_Add_New_Log(){
        ActivityHistoryImpl activityHistory = new ActivityHistoryImpl("ActivityTest");
        BoardImpl board = new BoardImpl(VALID_NAME_LENGTH);


        board.logActivityHistory("Test");

        Assertions.assertEquals(1,board.getActivityHistoryList().size());
    }
}
