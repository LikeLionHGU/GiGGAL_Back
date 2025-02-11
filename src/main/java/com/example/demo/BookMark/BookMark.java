package com.example.demo.BookMark;

import com.example.demo.Book.Book;
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
    private String time;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public static BookMark from(Book requestBook) {
        return BookMark.builder()
                .book(requestBook)
                .status("읽기 전")
                .time("0")
                .build();
    }

}
