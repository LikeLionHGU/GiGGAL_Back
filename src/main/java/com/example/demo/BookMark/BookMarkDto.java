package com.example.demo.BookMark;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkDto {

    private Long id;
    private Long bookId;
    private String userEmail;
    private String bookTitle;
    private String status;
    private String time;

    public static BookMarkDto from(BookMark bookMark) {
        return BookMarkDto.builder()
                .id(bookMark.getId())
                .bookId(bookMark.getBook().getId())
                .bookTitle(bookMark.getBook().getTitle())
                .status(bookMark.getStatus())
                .time(bookMark.getTime())
                .build();
    }

    public static List<BookMarkDto> from(List<BookMark> bookMarks) {
        return bookMarks.stream().map(BookMarkDto::from).collect(Collectors.toList());
    }

}
