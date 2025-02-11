package com.example.demo.Book;

import lombok.*;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

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
    private float difficultyScore;
    private String difficultyState;


    public static BookDto from(Book book) {
        float averageScoreForDifficulty = (float) book.getDifficultyScore()/book.getPageCount();
        String stateForDifficulty  = null;
        if(averageScoreForDifficulty > 3.5) {
            stateForDifficulty = "관련 지식이 필요해요.";
        }else if(averageScoreForDifficulty >= 2.5) {
            stateForDifficulty = "읽을만해요.";
        }else{
            stateForDifficulty = "술술 읽혀요.";
        }

        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pageCount(book.getPageCount())
                .publisher(book.getPublisher())
                .thumbnail(book.getThumbnail())
                .difficultyScore(averageScoreForDifficulty)
                .difficultyState(stateForDifficulty)
                .build();
    }

    public static List<BookDto> from(List<Book> books) {
        return books.stream().map(BookDto::from).collect(Collectors.toList());
    }
}
