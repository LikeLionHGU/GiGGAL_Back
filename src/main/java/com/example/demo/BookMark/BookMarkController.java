package com.example.demo.BookMark;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PutMapping("/reading/time{bookId}")
    public ResponseEntity<String> updateReadingTime(@PathVariable String bookId, @RequestBody BookMarkRequestForTime request) {
        String result = bookMarkService.updateReadingTime(bookId, request);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/reading/time/{bookId}")
    public ResponseEntity<BookMarkResponseForTime> getReadingTime(@PathVariable String bookId) {
        BookMarkDto bookMarkDto = bookMarkService.getReadingTime(bookId);
        return ResponseEntity.ok().body(BookMarkResponseForTime.from(bookMarkDto));
    }

}
