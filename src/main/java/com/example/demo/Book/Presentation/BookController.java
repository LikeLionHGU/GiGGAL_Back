package com.example.demo.Book.Presentation;

import com.example.demo.Book.Application.BookService;
import com.example.demo.Book.Domain.Book;
import com.example.demo.Book.Domain.BookRepository;
import com.example.demo.Book.Presentation.Request.BookRequest;
import com.example.demo.Book.Presentation.Request.BookRequestForDifficulty;
import com.example.demo.Book.Presentation.Response.BookResponseForBookMarkCountAndDifficulty;
import com.example.demo.Book.Presentation.Response.BookResponseWithBookMarkCount;
import com.example.demo.Book.Presentation.Response.BookResponseWithDifficulty;
import com.example.demo.BookMark.Application.BookMarkService;
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
    public ResponseEntity<List<BookResponseWithDifficulty>> getListOfBookWithDifficulty(@RequestParam String keyword) {
        List<BookResponseWithDifficulty> bookResponseWithDifficulties = bookService.getBooksWithDifficulty(keyword).stream().map(BookResponseWithDifficulty::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithDifficulties);
    }

    @GetMapping("/ranking/bookmark")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> getListOfBookWithBookMark(@RequestParam String keyword) {
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookService.getBooksWithBookMarkCount(keyword).stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }

    @GetMapping("/bookmarkNumber/difficulty/{googleBookId}")
    public ResponseEntity<BookResponseForBookMarkCountAndDifficulty> getBookCountAndDifficulty(@PathVariable String googleBookId) {
        BookResponseForBookMarkCountAndDifficulty bookResponse = BookResponseForBookMarkCountAndDifficulty.from(bookService.getBookMarkCountAndDifficulty(googleBookId));
        return ResponseEntity.ok().body(bookResponse);
    }
}
