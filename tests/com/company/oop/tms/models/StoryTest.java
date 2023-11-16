package com.company.oop.tms.models;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.FeedbackImpl;
import com.company.oop.tms.models.tasks.StoryImpl;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.models.tasks.enums.StatusStory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.ObjectInputFilter;

public class StoryTest {

    @Test
    public void should_CreateStory_When_ValidInput(){
        StoryImpl story = initializeStory();

        Assertions.assertEquals(1,story.getId());
        Assertions.assertEquals("Test Story title",story.getTitle());
        Assertions.assertEquals("This is a story test",story.getDescription());
        Assertions.assertEquals(Priority.LOW,story.getPriority());
        Assertions.assertEquals(Size.LARGE,story.getSize());
        Assertions.assertEquals("John Doe",story.getAssignee());
    }

    @Test
    public void get_Should_Return_Assignee(){
        StoryImpl story = initializeStory();

        Member assignee = new MemberImpl("John Doe");
        Assertions.assertEquals(assignee.getName(),story.getAssignee());
    }

    @Test
    public void get_Should_Return_Status(){
        StoryImpl story = initializeStory();

        Assertions.assertEquals(StatusStory.NOT_DONE,story.getStatus());
    }

    @Test
    public void get_Should_Return_Size(){
        StoryImpl story = initializeStory();

        Assertions.assertEquals(Size.LARGE,story.getSize());
    }

    @Test
    public void get_Should_Return_Priority(){
        StoryImpl story = initializeStory();

        Assertions.assertEquals(Priority.LOW,story.getPriority());
    }

    @Test
    public void change_Should_Return_ChangeStatusStory(){
        StoryImpl story = initializeStory();

        story.changeStatusStory(StatusStory.DONE);
        Assertions.assertEquals(StatusStory.DONE,story.getStatus());
    }

    @Test(expected = InvalidUserInputException.class)
    public void change_Should_ThrowException_When_ChangeRating(){
        StoryImpl story = initializeStory();
        story.changeStatusStory(StatusStory.NOT_DONE);
    }

    @Test
    public void change_Should_Return_ChangePriority(){
        StoryImpl story = initializeStory();

        story.changePriority(Priority.HIGH);
        Assertions.assertEquals(Priority.HIGH,story.getPriority());
    }

    @Test(expected = InvalidUserInputException.class)
    public void change_Should_ThrowException_When_ChangePriority(){
        StoryImpl story = initializeStory();
        story.changePriority(Priority.LOW);
    }

    @Test
    public void change_Should_Return_ChangeSize(){
        StoryImpl story = initializeStory();

        story.changeSize(Size.MEDIUM);
        Assertions.assertEquals(Size.MEDIUM,story.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void change_Should_ThrowException_When_ChangeSize(){
        StoryImpl story = initializeStory();
        story.changeSize(Size.LARGE);
    }


    public static StoryImpl initializeStory(){
        return new StoryImpl(1,"Test Story title", "This is a story test",
                Priority.LOW, Size.LARGE, new MemberImpl("John Doe"));
    }
}
