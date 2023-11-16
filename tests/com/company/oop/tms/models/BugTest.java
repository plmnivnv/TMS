package com.company.oop.tms.models;


import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.FeedbackImpl;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BugTest {

    @Test
    public void should_CreateFeedBack_When_ValidInput(){
        BugImpl bug = initializeBug();
        Assertions.assertEquals(1,bug.getId());
        Assertions.assertEquals("Test Bug title",bug.getTitle());
        Assertions.assertEquals("This is a test bug",bug.getDescription());
        Assertions.assertEquals(Priority.LOW,bug.getPriority());
        Assertions.assertEquals(Severity.CRITICAL,bug.getSeverity());
        Assertions.assertEquals("John Doe",bug.getAssignee().getName());
    }

    @Test
    public void getter_Should_Return_ValidAssignee(){
        Member assignee = new MemberImpl("John Doe");
        BugImpl bug = initializeBug();
        Assertions.assertEquals(assignee,bug.getAssignee());
    }

    @Test
    public void getter_Should_Return_ValidPriority(){
        BugImpl bug = initializeBug();
        Assertions.assertEquals(Priority.LOW,bug.getPriority());
    }

    @Test
    public void getter_Should_Return_ValidSeverity(){
        BugImpl bug = initializeBug();
        Assertions.assertEquals(Severity.CRITICAL,bug.getSeverity());
    }

    @Test
    public void change_Should_return_StatusBug(){
        BugImpl bug = initializeBug();
        bug.changeStatusBug(StatusBug.DONE);
        Assertions.assertEquals(StatusBug.DONE,bug.getStatusBug());
    }


    @Test
    public void change_Should_return_PriorityBug(){
        BugImpl bug = initializeBug();
        bug.changePriorityBug(Priority.HIGH);
        Assertions.assertEquals(Priority.HIGH,bug.getPriority());
    }

    @Test
    public void change_Should_return_SeverityBug(){
        BugImpl bug = initializeBug();
        bug.changeSeverityBug(Severity.MAJOR);
        Assertions.assertEquals(Severity.MAJOR,bug.getSeverity());
    }


    public static BugImpl initializeBug(){
        return new BugImpl(1,"Test Bug title","This is a test bug",
                null,Priority.LOW,Severity.CRITICAL,new MemberImpl("John Doe"));
    }


}
