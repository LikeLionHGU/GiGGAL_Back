package com.example.demo.BookMark;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PutMapping("/reading/time/{bookId}")
    public ResponseEntity<String> updateReadingTime(@PathVariable String bookId, @RequestBody BookMarkRequestForTime request) {
        String result = bookMarkService.updateReadingTime(bookId, request);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/reading/time/{bookId}")
    public ResponseEntity<String> getReadingTime(@PathVariable String bookId) {
        BookMarkDto bookMarkDto = bookMarkService.getReadingTime(bookId);
        return ResponseEntity.ok().body(bookMarkDto.getTime());
    }

    @PutMapping("/reading/{bookId}")
    public ResponseEntity<String> changeStatusToReading(@PathVariable String bookId){
        String message = bookMarkService.changeStatusToReading(bookId);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/complete/{bookId}")
    public ResponseEntity<String> changeStatusToComplete(@PathVariable String bookId){
        String message = bookMarkService.changeStatusToComplete(bookId);
        return ResponseEntity.ok(message);
    }
}
