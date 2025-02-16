package com.example.demo.Book.Presentation;

import com.example.demo.Book.Application.BookService;
import com.example.demo.Book.Domain.Book;
import com.example.demo.Book.Domain.BookRepository;
import com.example.demo.Book.Presentation.Request.BookRequest;
import com.example.demo.Book.Presentation.Request.BookRequestForDifficulty;
import com.example.demo.Book.Presentation.Request.BookRequestForListUp;
import com.example.demo.Book.Presentation.Response.BookResponseWithBookMarkCount;
import com.example.demo.Book.Presentation.Response.BookResponseWithDifficulty;
import com.example.demo.BookMark.Application.BookMarkService;
import com.example.demo.User.Application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BookMarkService bookMarkService;

    @PostMapping("/bookmark")
    public ResponseEntity<String> createBookAndBookMark(@RequestBody BookRequest bookRequest) {
        bookService.createBook(bookRequest);
        String email = bookRequest.getUserEmail();
        System.out.println("Email: " + email);
        Book requestBook = bookRepository.findByTitleAndAuthorAndPublisher(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublisher());
        String message = bookMarkService.addBookMark(requestBook, email);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/difficulty/{bookId}")
    public ResponseEntity<String> updateBookDifficulty(@PathVariable Long bookId, @RequestBody BookRequestForDifficulty bookRequestForDifficulty) {
        bookService.editBookDifficulty(bookId, bookRequestForDifficulty);
        return ResponseEntity.ok().body("난이도 평가 완료!");
    }

    @GetMapping("/ranking/difficulty")
    public ResponseEntity<List<BookResponseWithDifficulty>> getListOfBookWithDifficulty(@RequestBody BookRequestForListUp bookRequestForListUp) {
        List<BookResponseWithDifficulty> bookResponseWithDifficulties = bookService.getBooksWithDifficulty(bookRequestForListUp).stream().map(BookResponseWithDifficulty::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithDifficulties);
    }

    @GetMapping("/ranking/bookmark")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> getListOfBookWithBookMark(@RequestBody BookRequestForListUp bookRequestForListUp) {
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookService.getBooksWithBookMarkCount(bookRequestForListUp).stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }
}
