package com.example.demo.BookMark;

import com.example.demo.Book.BookResponseWithBookMarkCount;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    public ResponseEntity<String> getReadingTime(@PathVariable Long bookId) {
        BookMarkDto bookMarkDto = bookMarkService.getReadingTime(bookId);
        return ResponseEntity.ok().body(bookMarkDto.getTime());
    }

    @PutMapping("/reading/{bookId}")
    public ResponseEntity<String> changeStatusToReading(@PathVariable Long bookId){
        String message = bookMarkService.changeStatusToReading(bookId);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/complete/{bookId}")
    public ResponseEntity<String> changeStatusToComplete(@PathVariable Long bookId){
        String message = bookMarkService.changeStatusToComplete(bookId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/list/before/reading")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> makeListOfBeforeReading(){
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookMarkService.makeBookListOfBeforeReading().stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }

    @GetMapping("/list/now/reading")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> makeListOfNowReading(){
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookMarkService.makeBookListOfNowReading().stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }

    @GetMapping("/list/after/reading")
    public ResponseEntity<List<BookResponseWithBookMarkCount>> makeListOfAfterReading(){
        List<BookResponseWithBookMarkCount> bookResponseWithBookMarkCounts = bookMarkService.makeBookListOfAfterReading().stream().map(BookResponseWithBookMarkCount::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookResponseWithBookMarkCounts);
    }
}
