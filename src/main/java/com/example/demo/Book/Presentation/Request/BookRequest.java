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
    private String thumbnail;
    private String isbn;

    public BookRequest(String userEmail, String title, String author, String publisher, int pageCount, String thumbnail, String isbn) {
        this.userEmail = userEmail;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.thumbnail = thumbnail;
        this.isbn = isbn;
    }
}
