package com.company.oop.tms.models;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.FeedbackImpl;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FeedbackTest {

    @Test
    public void should_CreateFeedBack_When_ValidInput(){
        FeedbackImpl feedback = initializeFeedBack();
        Assertions.assertEquals(1,feedback.getId());
        Assertions.assertEquals("Test FeedBack",feedback.getTitle());
        Assertions.assertEquals("This is a test FeedBack",feedback.getDescription());
        Assertions.assertEquals(1,feedback.getRating());
    }

    @Test
    public void getter_Should_Return_ValidStatus(){
        FeedbackImpl feedback = initializeFeedBack();
        Assertions.assertEquals(StatusFeedback.NEW,feedback.getStatusFeedback());
    }

    @Test
    public void getter_Should_Return_Rating(){
        FeedbackImpl feedback = initializeFeedBack();
        Assertions.assertEquals(1,feedback.getRating());
    }

    @Test
    public void change_Should_Return_ChangeStatus(){
        FeedbackImpl feedback = initializeFeedBack();

        feedback.changeStatus(StatusFeedback.DONE);
        Assertions.assertEquals(StatusFeedback.DONE,feedback.getStatusFeedback());
    }

    @Test(expected = InvalidUserInputException.class)
    public void change_Should_ThrowException_When_ChangeStatus(){
        FeedbackImpl feedback = initializeFeedBack();
        feedback.changeStatus(StatusFeedback.NEW);
    }

    @Test
    public void change_Should_Return_ChangeRating(){
        FeedbackImpl feedback = initializeFeedBack();

        feedback.changeRating(2);
        Assertions.assertEquals(2,feedback.getRating());
    }

    @Test(expected = InvalidUserInputException.class)
    public void change_Should_ThrowException_When_ChangeRating(){
        FeedbackImpl feedback = initializeFeedBack();
        feedback.changeRating(1);
    }


    public static FeedbackImpl initializeFeedBack(){
        return new FeedbackImpl(1,"Test FeedBack","This is a test FeedBack",1);
    }
}
