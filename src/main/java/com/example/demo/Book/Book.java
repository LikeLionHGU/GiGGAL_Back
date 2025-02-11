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

    @Id
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int pageCount;
    private URL thumbnail;
    private int hardCount;
    private int normalCount;
    private int easyCount;


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
