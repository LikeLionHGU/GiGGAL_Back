package com.example.demo.memo.Domain;

import com.example.demo.Book.Domain.Book;
import com.example.demo.User.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByBookAndUser(Book book, User user);
}
