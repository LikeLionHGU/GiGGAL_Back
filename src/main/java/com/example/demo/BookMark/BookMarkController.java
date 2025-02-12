package com.example.demo.BookMark;

import lombok.AllArgsConstructor;
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
    public ResponseEntity<String> getReadingTime(@PathVariable String bookId) {
        BookMarkDto bookMarkDto = bookMarkService.getReadingTime(bookId);
        return ResponseEntity.ok().body(bookMarkDto.getTime());
    }

}
