package com.example.demo.Book.Presentation.Response;

import com.example.demo.Book.Application.BookDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Builder
@Getter
@Setter
public class BookResponseWithDifficulty {

    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private String thumbnail;
    private float difficultyScore;
    private String difficultyState;
    private String isbn;

    public static BookResponseWithDifficulty from(BookDto bookDto) {
        return BookResponseWithDifficulty.builder()
                .bookId(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .pageCount(bookDto.getPageCount())
                .thumbnail(bookDto.getThumbnail())
                .difficultyScore(bookDto.getDifficultyScore())
                .difficultyState(bookDto.getDifficultyState())
                .isbn(bookDto.getIsbn())
                .build();
    }

}
