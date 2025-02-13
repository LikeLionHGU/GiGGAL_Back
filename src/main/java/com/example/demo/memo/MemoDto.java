package com.example.demo.memo;

import com.example.demo.Book.Book;
import com.example.demo.Book.BookDto;
import lombok.*;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MemoDto from(Memo memo) {
        return MemoDto.builder()
                .bookTitle(memo.getBook().getTitle())
                .content(memo.getContent())
                .date(memo.getDate())
                .build();
    }

    public static List<MemoDto> from(List<Memo> memos) {
        return memos.stream().map(MemoDto::from).collect(Collectors.toList());
    }

}
