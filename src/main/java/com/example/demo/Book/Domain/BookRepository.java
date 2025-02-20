package com.example.demo.Book.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByGoogleBookId(String googleBookId);
    List<Book> findByTitleContainsOrBookDetailContains(String title, String bookDetail);
    List<Book> findTop4ByOrderByCountOfBookMarkDesc();
}
