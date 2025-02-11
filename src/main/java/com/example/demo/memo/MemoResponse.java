package com.example.demo.memo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Builder
@Getter
@Setter
public class MemoResponse {
    private String bookId;
    private String content;
    private String date;
    private String message;

    public static MemoResponse from(MemoDto memoDto) {
        return MemoResponse.builder()
                .bookId(memoDto.getBookId())
                .content(memoDto.getContent())
                .date(memoDto.getDate())
                .build();
    }

    public static MemoResponse from(MemoDto memoDto, String successMessage) {
        return MemoResponse.builder()
                .bookId(memoDto.getBookId())
                .content(memoDto.getContent())
                .date(memoDto.getDate())
                .message(successMessage)
                .build();
    }

}
