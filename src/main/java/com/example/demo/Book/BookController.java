package com.example.demo.Book;

import com.example.demo.BookMark.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BookMarkService bookMarkService;

    @PostMapping("/book/mark/{user_email}")
    public ResponseEntity<String> createBookAndBookMark(@PathVariable String user_email, @RequestBody BookRequest bookRequest) {
        bookService.createBook(bookRequest);
        Book requestBook = bookRepository.findByTitleAndAuthorAndPublisher(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublisher());
        String message = bookMarkService.addBookMark(requestBook);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/book/difficulty/{bookId}")
    public ResponseEntity<String> updateBookDifficulty(@PathVariable String bookId, @RequestBody BookRequestForDifficulty bookRequestForDifficulty) {
        bookService.editBookDifficulty(bookId, bookRequestForDifficulty);
        return ResponseEntity.ok().body("난이도 평가 완료!");
    }

    @GetMapping("/book/rankingOfDifficulty")
    public ResponseEntity<List<BookResponseWithDifficulty>> getListOfBookWithDifficulty(@RequestBody BookRequestForListUp bookRequestForListUp) {
        List<BookResponseWithDifficulty> bookResponseWithDifficulties = bookService.getBooksWithDifficulty(bookRequestForListUp).stream().map(BookResponseWithDifficulty::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithDifficulties);
    }
