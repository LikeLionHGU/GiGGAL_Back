package com.example.demo.Book.Presentation.Request;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class BookRequest {

    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private URL thumbnail;

    public BookRequest(String title, String author, String publisher, int pageCount, URL thumbnail) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.thumbnail = thumbnail;
    }
}
