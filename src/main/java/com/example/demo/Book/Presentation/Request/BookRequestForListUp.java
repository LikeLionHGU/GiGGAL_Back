package com.example.demo.Book.Presentation.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestForListUp {
    private String keyword;

    public BookRequestForListUp(String keyword) {
        this.keyword = keyword;
    }

}
