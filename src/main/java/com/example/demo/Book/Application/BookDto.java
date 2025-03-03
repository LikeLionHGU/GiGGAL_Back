package com.example.demo.Book.Application;

import com.example.demo.Book.Domain.Book;
import com.example.demo.BookMark.Domain.BookMark;
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

    private Long id;
    private String title;
    private String author;
    private int pageCount;
    private String publisher;
    private String bookDetail;
    private String thumbnail;
    private float difficultyScore;
    private String difficultyState;
    private int countOfBookMark;
    private String googleBookId;


    public static BookDto from(Book book) {
        float averageScoreForDifficulty = ((float) book.getDifficultyScore())/((float) book.getCountForDifficulty());
        String stateForDifficulty = null;
        if(averageScoreForDifficulty > 3.5) {
            stateForDifficulty = "관련 지식이 필요해요.";
        }else if(averageScoreForDifficulty >= 2.5) {
            stateForDifficulty = "읽을만해요.";
        }else if(averageScoreForDifficulty > 0) {
            stateForDifficulty = "술술 읽혀요.";
        }else{
            stateForDifficulty = "정보없음.";

        }

        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .pageCount(book.getPageCount())
                .publisher(book.getPublisher())
                .bookDetail(book.getBookDetail())
                .thumbnail(book.getThumbnail())
                .difficultyScore(averageScoreForDifficulty)
                .difficultyState(stateForDifficulty)
                .countOfBookMark(book.getCountOfBookMark())
                .googleBookId(book.getGoogleBookId())
                .build();
    }

    public static BookDto from(BookMark bookMark) {
        return BookDto.builder()
                .id(bookMark.getBook().getId())
                .title(bookMark.getBook().getTitle())
                .author(bookMark.getBook().getAuthor())
                .pageCount(bookMark.getBook().getPageCount())
                .publisher(bookMark.getBook().getPublisher())
                .bookDetail(bookMark.getBook().getBookDetail())
                .thumbnail(bookMark.getBook().getThumbnail())
                .countOfBookMark(bookMark.getBook().getCountOfBookMark())
                .googleBookId(bookMark.getBook().getGoogleBookId())
                .build();
    }

    public static List<BookDto> from(List<Book> books) {
        return books.stream().map(BookDto::from).collect(Collectors.toList());
    }
}
