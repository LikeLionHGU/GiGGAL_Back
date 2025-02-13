package com.example.demo.memo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Builder
@Getter
@Setter
public class MemoResponse {
    private String bookTitle;
    private String content;
    private String date;

    public static MemoResponse from(MemoDto memoDto) {
        return MemoResponse.builder()
                .bookTitle(memoDto.getBookTitle())
                .content(memoDto.getContent())
                .date(memoDto.getDate())
                .build();
    }

}
