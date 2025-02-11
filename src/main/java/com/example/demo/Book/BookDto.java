package com.example.demo.Book;

import lombok.*;

import java.net.URL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private String author;
    private int pageCount;
    private String publisher;
    private URL thumbnail;


    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pageCount(book.getPageCount())
                .publisher(book.getPublisher())
                .thumbnail(book.getThumbnail())
                .build();
    }

    public static BookDto from(BookRequest request) {
        return BookDto.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .thumbnail(request.getThumbnail())
                .pageCount(request.getPageCount())
                .build();
    }

}
