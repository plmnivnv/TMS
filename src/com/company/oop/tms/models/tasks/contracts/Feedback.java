package com.company.oop.tms.models.tasks.contracts;

import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.models.tasks.enums.StatusFeedback;

public interface Feedback extends Task {

    void changeStatus(StatusFeedback statusFeedback);

    void changeRating(int rating);
}
