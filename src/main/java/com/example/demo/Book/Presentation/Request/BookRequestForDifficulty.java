package com.example.demo.Book.Presentation.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestForDifficulty {

    private String difficulty;

    public BookRequestForDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
