package com.company.oop.tms.models.tasks;

import com.company.oop.tms.exceptions.InvalidUserInputException;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;

public class FeedbackImpl extends TasksImpl implements Feedback {

    public static final String STATUS_ERROR_MESSAGE = "Status is already at %s";
    public static final String STATUS_CHANGE_MESSAGE = "The status of item with ID: %d changed from %s to %s";
    public static final String RATING_ERROR_MESSAGE = "Rating is already %d.";
    public static final String RATING_CHANGED_MESSAGE = "The rating of item with ID: %d changed from %s to %s";
    private int rating;
    private StatusFeedback statusFeedback;


    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        this.statusFeedback = StatusFeedback.NEW;
        this.rating = rating;
    }
    @Override
    public StatusFeedback getStatusFeedback() {
        return statusFeedback;
    }
    @Override
    public int getRating() {
        return rating;
    }


    @Override
    public void changeStatus(StatusFeedback statusFeedback) {
        StatusFeedback currentStatus = getStatusFeedback();
        if (statusFeedback.equals(getStatusFeedback())) {
            throw new InvalidUserInputException(String.format(STATUS_ERROR_MESSAGE, getStatusFeedback()));
        }
        this.statusFeedback = statusFeedback;
        logActivityHistory(String.format(STATUS_CHANGE_MESSAGE,
                getId(),
                currentStatus,
                getStatusFeedback()));
    }

    @Override
    public void changeRating(int rating) {
        int currentRating = getRating();
        if (rating == getRating()){
            throw new InvalidUserInputException(String.format(RATING_ERROR_MESSAGE,getRating()));
        }
        this.rating = rating;
        logActivityHistory(String.format(RATING_CHANGED_MESSAGE, getId(), currentRating, getRating()));
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                Status: %s
                Rating: %d""",super.toString(),getStatusFeedback(),getRating());

    }
}
