package com.example.demo.memo;

import lombok.*;

import java.net.URL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {

    private String bookTitle;
    private String content;
    private String date;

    public static MemoDto from(Memo memo, String title) {
        return MemoDto.builder()
                .bookTitle(title)
                .content(memo.getContent())
                .date(memo.getDate())
                .build();
    }

    public static MemoDto from(MemoRequest request, String title) {
        return MemoDto.builder()
                .bookTitle(title)
                .content(request.getContent())
                .date(request.getDate())
                .build();
    }

}
