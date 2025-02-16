package com.example.demo.BookMark.Presentation;

import com.example.demo.Book.Presentation.Response.BookResponseWithBookMarkCount;
import com.example.demo.BookMark.Application.BookMarkDto;
import com.example.demo.BookMark.Application.BookMarkService;
import com.example.demo.BookMark.Presentation.Request.BookMarkRequestForTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PutMapping("/reading/time/{bookId}")
    public ResponseEntity<String> updateReadingTime(@PathVariable Long bookId, @RequestBody BookMarkRequestForTime request) {
        String result = bookMarkService.updateReadingTime(bookId, request);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/reading/time/{bookId}")
    public ResponseEntity<String> getReadingTime(@PathVariable Long bookId, @RequestBody String userEmail) {
        BookMarkDto bookMarkDto = bookMarkService.getReadingTime(bookId, userEmail);
        return ResponseEntity.ok().body(bookMarkDto.getTime());
    }

    @PutMapping("/reading/{bookId}")
    public ResponseEntity<String> changeStatusToReading(@PathVariable Long bookId, @RequestBody String userEmail){
        String message = bookMarkService.changeStatusToReading(bookId, userEmail);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/complete/{bookId}")
    public ResponseEntity<String> changeStatusToComplete(@PathVariable Long bookId, @RequestBody String userEmail){
        String message = bookMarkService.changeStatusToComplete(bookId, userEmail);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/list/before/reading")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> makeListOfBeforeReading(@RequestBody String userEmail){
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookMarkService.makeBookListOfBeforeReading(userEmail).stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }

    @GetMapping("/list/now/reading")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> makeListOfNowReading(@RequestBody String userEmail){
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookMarkService.makeBookListOfNowReading(userEmail).stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }

    @GetMapping("/list/after/reading")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> makeListOfAfterReading(@RequestBody String userEmail){
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookMarkService.makeBookListOfAfterReading(userEmail).stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }
}
