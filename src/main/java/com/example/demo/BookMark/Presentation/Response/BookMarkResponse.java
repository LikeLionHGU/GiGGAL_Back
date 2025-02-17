package com.example.demo.BookMark.Presentation.Response;

import com.example.demo.Book.Application.BookDto;
import com.example.demo.Book.Presentation.Response.BookResponseWithBookMarkCount;
import com.example.demo.BookMark.Application.BookMarkDto;
import com.example.demo.BookMark.Domain.BookMark;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookMarkResponse {

    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private String thumbnail;
    private int countOfBookMark;
    private String time;
    private String isbn;

    public static BookMarkResponse from(BookMarkDto bookMarkDto) {
        return BookMarkResponse.builder()
                .bookId(bookMarkDto.getBookId())
                .title(bookMarkDto.getBook().getTitle())
                .author(bookMarkDto.getBook().getAuthor())
                .publisher(bookMarkDto.getBook().getPublisher())
                .pageCount(bookMarkDto.getBook().getPageCount())
                .thumbnail(bookMarkDto.getBook().getThumbnail())
                .countOfBookMark(bookMarkDto.getBook().getCountOfBookMark())
                .time(bookMarkDto.getTime())
                .isbn(bookMarkDto.getBook().getIsbn())
                .build();
    }
}
