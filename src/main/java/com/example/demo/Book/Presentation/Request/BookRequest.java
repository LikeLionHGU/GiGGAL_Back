package com.example.demo.Book.Presentation.Request;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class BookRequest {

    private String userEmail;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private String bookDetail;
    private String thumbnail;
    private String googleBookId;

    public BookRequest(String userEmail, String title, String author, String publisher, int pageCount, String bookDetail, String thumbnail, String googleBookId) {
        this.userEmail = userEmail;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.bookDetail = bookDetail;
        this.thumbnail = thumbnail;
        this.googleBookId = googleBookId;
    }
}
