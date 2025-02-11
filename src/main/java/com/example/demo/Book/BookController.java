package com.example.demo.Book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book/mark/{user_email}")
    public ResponseEntity<String> createBookAndBookMark(@PathVariable String user_email, @RequestBody BookRequest bookRequest) {
        bookService.createBook(bookRequest);
        String message = "Successfully created book"; // 북마크 기능까지 구현하면 "북마크 성공!"이라는 메세지로 바꿔주세요!
        return ResponseEntity.ok(message);
    }
}
