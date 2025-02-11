package com.example.demo.Book;

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
