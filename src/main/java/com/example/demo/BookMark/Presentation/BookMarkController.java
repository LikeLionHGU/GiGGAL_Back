package com.example.demo.BookMark.Presentation;

import com.example.demo.Book.Presentation.Response.BookResponseWithBookMarkCount;
import com.example.demo.BookMark.Application.BookMarkDto;
import com.example.demo.BookMark.Application.BookMarkService;
import com.example.demo.BookMark.Presentation.Request.BookMarkRequestForTime;
import com.example.demo.BookMark.Presentation.Response.BookMarkResponse;
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

    @GetMapping("/reading/time/{isbn}")
    public ResponseEntity<String> getReadingTime(@PathVariable String isbn, @RequestParam String userEmail) {
        BookMarkDto bookMarkDto = bookMarkService.getReadingTime(isbn, userEmail);
        return ResponseEntity.ok().body(bookMarkDto.getTime());
    }

    @PutMapping("/reading/{bookId}")
    public ResponseEntity<String> changeStatusToReading(@PathVariable Long bookId, @RequestParam String userEmail){
        String message = bookMarkService.changeStatusToReading(bookId, userEmail);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/complete/{bookId}")
    public ResponseEntity<String> changeStatusToComplete(@PathVariable Long bookId, @RequestParam String userEmail){
        String message = bookMarkService.changeStatusToComplete(bookId, userEmail);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/list/before/reading")
    public ResponseEntity<List<BookMarkResponse>> makeListOfBeforeReading(@RequestParam String userEmail){
        List<BookMarkResponse> bookMarkResponse = bookMarkService.makeBookListOfBeforeReading(userEmail).stream().map(BookMarkResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookMarkResponse);
    }

    @GetMapping("/list/now/reading")
    public ResponseEntity<List<BookMarkResponse>> makeListOfNowReading(@RequestParam String userEmail){
        List<BookMarkResponse> bookMarkResponse = bookMarkService.makeBookListOfNowReading(userEmail).stream().map(BookMarkResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookMarkResponse);
    }

    @GetMapping("/list/after/reading")
    public ResponseEntity<List<BookMarkResponse>> makeListOfAfterReading(@RequestParam String userEmail){
        List<BookMarkResponse> bookMarkResponse = bookMarkService.makeBookListOfAfterReading(userEmail).stream().map(BookMarkResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(bookMarkResponse);
    }
}
