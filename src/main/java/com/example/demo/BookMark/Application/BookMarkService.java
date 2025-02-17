package com.example.demo.BookMark.Application;

import com.example.demo.Book.Domain.Book;
import com.example.demo.Book.Application.BookDto;
import com.example.demo.Book.Domain.BookRepository;
import com.example.demo.BookMark.Domain.BookMark;
import com.example.demo.BookMark.Domain.BookMarkRepository;
import com.example.demo.BookMark.Presentation.Request.BookMarkRequestForTime;
import com.example.demo.User.Domain.User;
import com.example.demo.User.Domain.UserRepository;
import com.example.demo.User.Application.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public String addBookMark(Book requestBook, String userEmail) {
        String email = userEmail;
        User requestUser = userRepository.findByEmail(email);
        Book book = bookRepository.findByTitleAndAuthorAndPublisher(requestBook.getTitle(), requestBook.getAuthor(), requestBook.getPublisher());
        BookMark bookMark = bookMarkRepository.findByBookIdAndUserEmail(requestBook.getId(), email);
        if(bookMark == null){
            bookMarkRepository.save(BookMark.from(requestBook, requestUser));
            return "북마크에 성공하였습니다.";
        }else{
            return "이미 북마크 되어 있는 책입니다.";
        }
    }

    @Transactional
    public String updateReadingTime(Long bookId, BookMarkRequestForTime request) {
        String email = request.getUserEmail();
        BookMark bookMark = bookMarkRepository.findByBookIdAndUserEmail(bookId, email);
        int originalTime = bookMark.getTime();
        bookMark.setTime(originalTime + request.getTime());
        return "시간 저장 성공!";
    }

    public BookMarkDto getReadingTime(Long bookId, String userEmail) {
        String email = userEmail;
        BookMarkDto bookMarkDto = BookMarkDto.from(bookMarkRepository.findByBookIdAndUserEmail(bookId, email));
        return bookMarkDto;
    }

    @Transactional
    public String changeStatusToReading(Long bookId, String userEmail) {
        BookMark targetBookMark = bookMarkRepository.findByBookIdAndUserEmail(bookId, userEmail);
        targetBookMark.setStatus("읽는 중");
        return targetBookMark.getStatus();
    }

    @Transactional
    public String changeStatusToComplete(Long bookId, String userEmail) {
        BookMark targetBookMark = bookMarkRepository.findByBookIdAndUserEmail(bookId, userEmail);
        targetBookMark.setStatus("완독");
        return targetBookMark.getStatus();
    }

    public List<BookDto> makeBookListOfBeforeReading(String userEmail) {
        String email = userEmail;
        List<BookDto> bookDtoList = bookMarkRepository.findByUserEmailAndStatus(email, "읽기 전").stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }

    public List<BookDto> makeBookListOfNowReading(String userEmail) {
        String email = userEmail;
        List<BookDto> bookDtoList = bookMarkRepository.findByUserEmailAndStatus(email, "읽는 중").stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }

    public List<BookDto> makeBookListOfAfterReading(String userEmail) {
        String email = userEmail;
        List<BookDto> bookDtoList = bookMarkRepository.findByUserEmailAndStatus(email, "완독").stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }
}
