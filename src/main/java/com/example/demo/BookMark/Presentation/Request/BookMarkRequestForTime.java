package com.example.demo.BookMark.Presentation.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkRequestForTime {
    private int time;

    public BookMarkRequestForTime(int time) {
        this.time = time;
    }
}
