package com.example.demo.memo.Presentation.Response;

import com.example.demo.memo.Application.MemoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemoResponse {
    private Long memoId;
    private String bookTitle;
    private String content;
    private String date;

    public static MemoResponse from(MemoDto memoDto) {
        return MemoResponse.builder()
                .memoId(memoDto.getId())
                .bookTitle(memoDto.getBookTitle())
                .content(memoDto.getContent())
                .date(memoDto.getDate())
                .build();
    }

}
