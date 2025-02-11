package com.example.demo.BookMark;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

//    @GetMapping("/mark_count/{bookId}")
//    public Long countBookMarks(@PathVariable Long bookId) {
//        Long numberOfBookMarksForThisBook = bookMarkService.countBookMarks(bookId);
//        return numberOfBookMarksForThisBook;
//    }

}
