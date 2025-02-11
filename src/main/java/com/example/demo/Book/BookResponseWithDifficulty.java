package com.example.demo.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Builder
@Getter
@Setter
public class BookResponseWithDifficulty {

    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private URL thumbnail;
    private float difficultyScore;
    private String difficultyState;

    public static BookResponseWithDifficulty from(BookDto bookDto) {
        return BookResponseWithDifficulty.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .pageCount(bookDto.getPageCount())
                .thumbnail(bookDto.getThumbnail())
                .difficultyScore(bookDto.getDifficultyScore())
                .difficultyState(bookDto.getDifficultyState())
                .build();
    }

}
