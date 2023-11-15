package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.Comment;
import com.company.oop.tms.models.contracts.Member;

public class CommentImpl implements Comment {

    private Member author;
    private String content;


    public CommentImpl(Member author, String content) {
        this.author = author;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Member getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("""
                Author: %s
                Content: %s""", getAuthor(), getContent());
    }
}
