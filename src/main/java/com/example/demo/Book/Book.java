package com.example.demo.Book;

import com.example.demo.BookMark.BookMark;
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
    private Long pageCount;
    private URL thumbnail;

    @OneToMany(
            mappedBy = "book",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<BookMark> bookMarks = new ArrayList<>();

    public static Book from(BookRequest request) {
        return  Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .pageCount(request.getPageCount())
                .thumbnail(request.getThumbnail())
                .build();
    }
}
