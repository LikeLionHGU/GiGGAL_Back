package com.example.demo.Book;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class BookRequest {

    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private URL thumbnail;

    public BookRequest(String bookId , String title, String author, String publisher, int pageCount, URL thumbnail) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.thumbnail = thumbnail;
    }
}
