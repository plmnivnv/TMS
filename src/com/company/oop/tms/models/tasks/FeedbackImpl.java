package com.company.oop.tms.models.tasks;

import com.company.oop.tms.models.contracts.ActivityHistory;
import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.tasks.contracts.Feedback;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;

import java.util.List;

public class FeedbackImpl extends TasksImpl implements Feedback {

    private int rating;
    private StatusFeedback statusFeedback;


    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        this.statusFeedback = StatusFeedback.NEW;
        this.rating = rating;
    }

    public StatusFeedback getStatusFeedback() {
        return statusFeedback;
    }

    public int getRating() {
        return rating;
    }


    @Override
    public void changeStatus(StatusFeedback statusFeedback) {
        logActivityHistory(String.format("Status changed from %s to %s", statusFeedback, getStatusFeedback()));
        this.statusFeedback = statusFeedback;
    }

    @Override
    public void changeRating(int rating) {
        logActivityHistory(String.format("Rating changed from %s to %s", rating, getRating()));
        this.rating = rating;
    }


}
