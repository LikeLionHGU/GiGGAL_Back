package com.example.demo.BookMark;

import com.example.demo.Book.Book;
import com.example.demo.Book.BookRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String changeStatusToReading(String bookId){
        BookMark targetBookMark = bookMarkRepository.findByBookIdAndUserEmail(bookId, userService.getUserEmail());
        targetBookMark.setStatus("읽는 중");
        return targetBookMark.getStatus();
    }

    public String changeStatusToComplete(String bookId){
        BookMark targetBookMark = bookMarkRepository.findByBookIdAndUserEmail(bookId, userService.getUserEmail());
        targetBookMark.setStatus("완독");
        return targetBookMark.getStatus();
    }

    public List<BookMarkDtoList> makeBookMarkListOfBeforeReading(){
        String email = userService.getUserEmail();
        List<BookMark> BookMarkListOfBeforeReading = bookMarkRepository.findByUserEmailAndStatus(email, "읽기 전");
        return BookMarkDtoList.from(BookMarkListOfBeforeReading);
    }
}
