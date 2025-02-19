package com.example.demo.Book.Domain;

import com.example.demo.Book.Presentation.Request.BookRequest;
import com.example.demo.BookMark.Domain.BookMark;
import com.example.demo.memo.Domain.Memo;
import jakarta.persistence.*;
import lombok.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private String bookDetail;
    private String thumbnail;
    private int countForDifficulty;
    private int difficultyScore;
    private int countOfBookMark;
    private String googleBookId;


    @OneToMany(
            mappedBy = "book",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<BookMark> bookMarks = new ArrayList<>();

    @OneToMany(
            mappedBy = "book",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Memo> memos = new ArrayList<>();

    public static Book from(BookRequest request) {
        return  Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .pageCount(request.getPageCount())
                .bookDetail(request.getBookDetail())
                .thumbnail(request.getThumbnail())
                .countOfBookMark(0)
                .googleBookId(request.getGoogleBookId())
                .build();
    }
}
