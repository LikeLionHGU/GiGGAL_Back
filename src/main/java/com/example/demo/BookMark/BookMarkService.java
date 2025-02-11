package com.example.demo.BookMark;

import com.example.demo.Book.Book;
import com.example.demo.Book.BookDto;
import com.example.demo.Book.BookRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public String addBookMark(Book requestBook) {
        String email = userService.getUserEmail();
        User requestUser = userRepository.findByEmail(email);
        bookMarkRepository.save(BookMark.from(requestBook, requestUser));
        return "북마크에 성공하였습니다.";
    }

    @Transactional
    public String updateReadingTime(String bookId, BookMarkRequestForTime request) {
        String email = userService.getUserEmail();
        BookMark bookMark = bookMarkRepository.findByBookIdAndUserEmail(bookId, email);
        int originalTime = bookMark.getTime();
        bookMark.setTime(originalTime + request.getTime());
        return "시간 저장 성공!";
    }

    public BookMarkDto getReadingTime(String bookId) {
        String email = userService.getUserEmail();
        BookMarkDto bookMarkDto = BookMarkDto.from(bookMarkRepository.findByBookIdAndUserEmail(bookId, email));
        return bookMarkDto;
    }

}
