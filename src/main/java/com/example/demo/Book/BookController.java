package com.example.demo.Book;

import com.example.demo.BookMark.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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



}
