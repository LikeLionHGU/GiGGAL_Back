package com.example.demo.BookMark.Application;

import com.example.demo.Book.Domain.Book;
import com.example.demo.BookMark.Domain.BookMark;
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
    private Book book;
    private String userEmail;
    private String bookTitle;
    private String status;
    private String time;
    private String googleBookId;

    public static BookMarkDto from(BookMark bookMark) {
        String timeForString = timeToString(bookMark);

        return BookMarkDto.builder()
                .id(bookMark.getId())
                .book(bookMark.getBook())
                .bookId(bookMark.getBook().getId())
                .bookTitle(bookMark.getBook().getTitle())
                .status(bookMark.getStatus())
                .time(timeForString)
                .googleBookId(bookMark.getBook().getGoogleBookId())
                .build();
    }

    public static String timeToString(BookMark bookMark) {
        String readingTimeByString = null;

        if(bookMark.getTime() < 60) {
            if(bookMark.getTime() < 10) {
                readingTimeByString = "00시간 " + "0" + bookMark.getTime() + "분";
            }else{
                readingTimeByString = "00시간 " + bookMark.getTime() + "분";
            }
        }else{
            if(bookMark.getTime()/60 < 10 && bookMark.getTime()%60 < 10) {
                readingTimeByString = "0" + bookMark.getTime() / 60 + "시간 " + "0" + bookMark.getTime() % 60 + "분";
            }else if(bookMark.getTime()/60 < 10 && bookMark.getTime()%60 > 10) {
                readingTimeByString = "0" + bookMark.getTime() / 60 + "시간 " + bookMark.getTime() % 60 + "분";
            }else if(bookMark.getTime()/60 > 10 && bookMark.getTime()%60 < 10) {
                readingTimeByString = bookMark.getTime() / 60 + "시간 " + "0" + bookMark.getTime() % 60 + "분";
            }else{
                readingTimeByString = bookMark.getTime() / 60 + "시간 " + bookMark.getTime() % 60 + "분";
            }
        }
        return readingTimeByString;
    }

    public static List<BookMarkDto> from(List<BookMark> bookMarks) {
        return bookMarks.stream().map(BookMarkDto::from).collect(Collectors.toList());
    }

}
