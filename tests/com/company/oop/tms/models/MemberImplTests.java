package com.company.oop.tms.models;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class MemberImplTests {
    private static final int NAME_MIN_LENGTH = 5;

    public String VALID_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH + 1);
    public String INVALID_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH - 1);


    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);

        Assertions.assertEquals(VALID_NAME_LENGTH, member.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> new MemberImpl(INVALID_NAME_LENGTH));
    }

    @Test
    public void getName_Should_Return_Name() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);

        Assertions.assertEquals(VALID_NAME_LENGTH, member.getName());
    }

    @Test
    public void getHistory_Should_Return_Copy_Of_List() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        List<ActivityHistoryImpl> activityHistoriesReference = member.getActivityHistoryList();
        List<ActivityHistoryImpl> sameReference = member.getActivityHistoryList();

        Assertions.assertEquals(activityHistoriesReference, sameReference);
    }
    @Test
    public void getTask_Should_Return_Copy_Of_List() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        List<Task> taskListReference = member.getTaskList();
        List<Task> sameReference = member.getTaskList();

        Assertions.assertEquals(taskListReference, sameReference);
    }

    @Test
    public void assignTask_Should_Add_Task_When_Task_Is_Assigned() {
        List<String> testStep = new ArrayList<>();
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        Task task = new BugImpl(1, "TestBugTitle", "Description", testStep, Priority.LOW, Severity.MINOR, member);

        member.assignTask(task);

        Assertions.assertEquals(1,member.getTaskList().size());
    }

    @Test
    public void UnAssignTask_Should_Removed_Task_When_Task_Is_UnAssigned() {
        List<String> testStep = new ArrayList<>();
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        Task task = new BugImpl(1, "TestBugTitle", "Description", testStep, Priority.LOW, Severity.MINOR, member);

        member.assignTask(task);
        member.unAssignTask(task);

        Assertions.assertEquals(0, member.getTaskList().size());
    }

    @Test
    public void logActivityHistory_Should_Add_NewHistory() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);

        member.logActivityHistory("Test");

        Assertions.assertEquals(1, member.getActivityHistoryList().size());
    }

}
