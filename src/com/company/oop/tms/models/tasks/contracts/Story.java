package com.company.oop.tms.models.tasks.contracts;

import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Size;
import com.company.oop.tms.models.tasks.enums.StatusStory;

public interface Story extends Task {

    String getAssignee();
    StatusStory getStatus();
    Size getSize();
    Priority getPriority();
    void changeStatusStory(StatusStory statusStory);
    void changePriority(Priority priority);
    void changeSize(Size size);


}
