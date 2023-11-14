package com.company.oop.tms;

import com.company.oop.tms.models.MemberImpl;
import com.company.oop.tms.models.contracts.Member;
import com.company.oop.tms.models.tasks.BugImpl;
import com.company.oop.tms.models.tasks.contracts.Bug;
import com.company.oop.tms.models.tasks.contracts.Task;
import com.company.oop.tms.models.tasks.enums.Priority;
import com.company.oop.tms.models.tasks.enums.Severity;
import com.company.oop.tms.models.tasks.enums.StatusBug;

import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void main(String[] args) {

        List<String> steps = new ArrayList<>();
        steps.add("adadad");
        Member member = new MemberImpl("Gosho");

        BugImpl bug = new BugImpl(1, "aaaaaaaaaaaaaaaaa", "DESCRITPIONSADA", steps, Priority.HIGH, Severity.MAJOR,member);

        BugImpl bug2 = new BugImpl(2, "aaaaaaaaaaaabaaaa", "DESCRITPIONaADA", steps, Priority.HIGH, Severity.MAJOR,member);

//        bug.changeStatusBug(StatusBug.DONE);
//        System.out.println(bug.getActivityHistoryList());
        bug.changeStatusBug(StatusBug.DONE);
//        bug.changeStatusBug(StatusBug.ACTIVE);
        bug2.changeStatusBug(StatusBug.DONE);

        System.out.println(bug2.getActivityHistoryList());



    }

}
