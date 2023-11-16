package com.company.oop.tms.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.TestUtils;

public class CommentImplTests {
    public String VALID_NAME_LENGTH = "Coment";
    private static final int NAME_MIN_LENGTH = 5;
    public String VALID_MEMBER_NAME_LENGTH = TestUtils.getString(NAME_MIN_LENGTH + 1);
    @Test
    public void constructor_Should_InitializeComment_When_Content_Is_Given() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        CommentImpl comment = new CommentImpl(member,VALID_NAME_LENGTH);

        Assertions.assertEquals(VALID_NAME_LENGTH, comment.getContent());
    }
    @Test
    public void constructor_Should_InitializeComment_When_Member_Is_Given() {
        MemberImpl member = new MemberImpl(VALID_NAME_LENGTH);
        CommentImpl comment = new CommentImpl(member,VALID_NAME_LENGTH);

        Assertions.assertEquals(member, comment.getAuthor());
    }
    
}
