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
        String timeForString = timeToString(bookMark);

        return BookMarkDto.builder()
                .id(bookMark.getId())
                .bookId(bookMark.getBook().getId())
                .bookTitle(bookMark.getBook().getTitle())
                .status(bookMark.getStatus())
                .time(timeForString)
                .build();
    }

    public static String timeToString(BookMark bookMark) {
        String readingTimeByString = null;

        if(bookMark.getTime() < 60) {
            readingTimeByString = "" + bookMark.getTime();
        }else{
            readingTimeByString = bookMark.getTime() / 60 + "시간 " + bookMark.getTime() % 60 + "분";
        }
        return readingTimeByString;
    }

    public static List<BookMarkDto> from(List<BookMark> bookMarks) {
        return bookMarks.stream().map(BookMarkDto::from).collect(Collectors.toList());
    }

}
