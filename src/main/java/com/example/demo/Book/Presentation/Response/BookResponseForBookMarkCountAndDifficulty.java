package com.example.demo.Book.Presentation.Response;

import com.example.demo.Book.Application.BookDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookResponseForBookMarkCountAndDifficulty {

    private int bookmarkCount;
    private String difficultyState;

    public static BookResponseForBookMarkCountAndDifficulty from(BookDto bookDto) {
        return BookResponseForBookMarkCountAndDifficulty.builder()
                .bookmarkCount(bookDto.getCountOfBookMark())
                .difficultyState(bookDto.getDifficultyState())
                .build();
    }

}
