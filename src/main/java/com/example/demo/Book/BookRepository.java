package com.example.demo.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthorAndPublisher(String title, String author, String publisher);
    Book findById(String id);
    List<Book> findByTitleContains(String title);
}
