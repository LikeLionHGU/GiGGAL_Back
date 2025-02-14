package com.example.demo.Book.Presentation.Response;

import com.example.demo.Book.Application.BookDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Builder
@Getter
@Setter
public class BookResponseWithBookMarkCount {

    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private URL thumbnail;
    private int countOfBookMark;

    public static BookResponseWithBookMarkCount from(BookDto bookDto) {
        return BookResponseWithBookMarkCount.builder()
                .bookId(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .pageCount(bookDto.getPageCount())
                .thumbnail(bookDto.getThumbnail())
                .countOfBookMark(bookDto.getCountOfBookMark())
                .build();
    }

}
