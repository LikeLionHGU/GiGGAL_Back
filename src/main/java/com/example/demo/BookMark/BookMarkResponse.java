package com.example.demo.BookMark;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class BookMarkResponse {
    String bookId;
    String title;
    String authors;
    int pageCount;
    String publisher;
    URL thumbnail;

    public static BookMarkResponse from(BookMarkDtoList bookMarkDtoList) {
        return BookMarkResponse.builder()
                .bookId(bookMarkDtoList.getBookId())
                .title(bookMarkDtoList.getTitle())
                .authors(bookMarkDtoList.getAuthors())
                .pageCount(bookMarkDtoList.getPageCount())
                .publisher(bookMarkDtoList.getPublisher())
                .thumbnail(bookMarkDtoList.getThumbnail())
                .build();
    }

    public static List<BookMarkResponse> from(List<BookMarkDtoList> bookMarkDtoList) {
        List<BookMarkResponse> bookMarkResponses = new ArrayList<>();
        for (BookMarkDtoList eachBookMarkDto : bookMarkDtoList) {
            bookMarkResponses.add(BookMarkResponse.from(eachBookMarkDto));
        }
        return bookMarkResponses;
    }
}
