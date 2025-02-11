package com.example.demo.BookMark;

import com.example.demo.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
    BookMark findByBookIdAndUserEmail(String bookId, String email);
}
