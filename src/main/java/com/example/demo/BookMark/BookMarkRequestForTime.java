package com.example.demo.BookMark;

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
