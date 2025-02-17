package com.example.demo.Book.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
    Book findByTitleAndAuthorAndPublisher(String title, String author, String publisher);
    List<Book> findByTitleContains(String title);
}
