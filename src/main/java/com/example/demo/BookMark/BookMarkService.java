package com.example.demo.BookMark;

import com.example.demo.Book.Book;
import com.example.demo.Book.BookDto;
import com.example.demo.Book.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final BookRepository bookRepository;

    public String addBookMark(Book requestBook) {
        bookMarkRepository.save(BookMark.from(requestBook));
        return "북마크에 성공하였습니다.";
    }

    public Long countBookMarks(Long bookId) {
        Long numberOfPeopleWhoBookMarkThisBook = bookMarkRepository.countByBook(bookId);
        return numberOfPeopleWhoBookMarkThisBook;
    }
}
