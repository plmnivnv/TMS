package com.company.oop.tms.models;

import com.company.oop.tms.models.contracts.Comment;

public class CommentImpl implements Comment {

    private String author;
    private String content;


    public CommentImpl(String author, String content) {
        this.author = author;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
