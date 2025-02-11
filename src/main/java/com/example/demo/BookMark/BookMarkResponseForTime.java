package com.example.demo.BookMark;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BookMarkResponseForTime {

    private String time;

    public static BookMarkResponseForTime from(BookMarkDto bookMarkDto) {
        return BookMarkResponseForTime.builder()
                .time(bookMarkDto.getTime())
                .build();
    }

}
