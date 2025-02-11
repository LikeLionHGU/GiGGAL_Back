package com.example.demo.memo;

import lombok.*;

import java.net.URL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {

    private String bookId;
    private String content;
    private String date;

    public static MemoDto from(Memo memo) {
        return MemoDto.builder()
                .bookId(memo.getBookId())
                .content(memo.getContent())
                .date(memo.getDate())
                .build();
    }

    public static MemoDto from(MemoRequest request, String id) {
        return MemoDto.builder()
                .bookId(id)
                .content(request.getContent())
                .date(request.getDate())
                .build();
    }

}
