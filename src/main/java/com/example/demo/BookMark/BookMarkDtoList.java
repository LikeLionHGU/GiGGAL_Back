package com.example.demo.BookMark;

import com.example.demo.Book.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class BookMarkDtoList {
    String bookId;
    String title;
    String authors;
    int pageCount;
    String publisher;
    URL thumbnail;

    public static BookMarkDtoList from(Book book) {
        return BookMarkDtoList.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .authors(book.getAuthor())
                .pageCount(book.getPageCount())
                .publisher(book.getPublisher())
                .thumbnail(book.getThumbnail())
                .build();
    }

    public static List<BookMarkDtoList> from(List<BookMark> bookMarks) {
        List<BookMarkDtoList> bookMarkDtos = new ArrayList<>();
        for (BookMark eachBookMark : bookMarks) {
            bookMarkDtos.add(BookMarkDtoList.from(eachBookMark.getBook()));
        }
        return bookMarkDtos;
    }
}
