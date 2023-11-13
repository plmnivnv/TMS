package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERROR = String.format("Team's name must be between %d and %d symbols",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    private String name;
    private List<Task> taskList;

    public MemberImpl(String name) {
        setName(name);
        taskList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name,
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);
        this.name = name;
    }

}
