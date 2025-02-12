package com.example.demo.BookMark;

import com.example.demo.Book.Book;
import com.example.demo.User.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private int time;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public static BookMark from(Book requestBook, User requestUser) {
        return BookMark.builder()
                .user(requestUser)
                .book(requestBook)
                .status("읽기 전")
                .time(0)
                .build();
    }
}
