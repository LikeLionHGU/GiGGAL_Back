package com.example.demo.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Builder
@Getter
@Setter
public class BookResponse {

    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private URL thumbnail;
    private int countOfBookMark;

    public static BookResponse from(BookDto bookDto) {
        return BookResponse.builder()
                .bookId(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .pageCount(bookDto.getPageCount())
                .thumbnail(bookDto.getThumbnail())
                .build();
    }

}
