package com.example.demo.memo;

import com.example.demo.Book.Book;
import com.example.demo.User.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;
    private String content;
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public static Memo createMemo(MemoRequest memoRequest, User loginUser, Book myBook) {
        return  Memo.builder()
                .user(loginUser)
                .book(myBook)
                .content(memoRequest.getContent())
                .date(memoRequest.getDate())
                .build();
    }
}
