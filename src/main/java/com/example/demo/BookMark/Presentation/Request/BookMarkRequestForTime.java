package com.example.demo.BookMark.Presentation.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkRequestForTime {

    private int time;
    private String userEmail;

    public BookMarkRequestForTime(int time, String userEmail) {

        this.time = time;
        this.userEmail = userEmail;

    }
}
