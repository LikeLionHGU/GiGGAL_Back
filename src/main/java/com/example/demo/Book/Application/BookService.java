package com.example.demo.Book.Application;

import com.example.demo.Book.Domain.BookRepository;
import com.example.demo.Book.Domain.Book;
import com.example.demo.Book.Presentation.Request.BookRequest;
import com.example.demo.Book.Presentation.Request.BookRequestForDifficulty;
import com.example.demo.Book.Presentation.Request.BookRequestForListUp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void createBook(BookRequest bookRequest) {
        Book book = bookRepository.findByTitleAndAuthorAndPublisher(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublisher());
        if(book == null) {
           BookDto bookDto = BookDto.from(bookRepository.save(Book.from(bookRequest)));
        }else{
            updateBookMarkCount(book);
        }
    }

    public void updateBookMarkCount(Book book){
        int newCountOfBookMark = book.getCountOfBookMark() + 1;
        book.setCountOfBookMark(newCountOfBookMark);
    }

    public List<BookDto> getBooksWithBookMarkCount(BookRequestForListUp bookRequestForListUp) {
        List<BookDto> bookDtoList = bookRepository.findAll().stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }

    @Transactional
    public void editBookDifficulty(Long bookId, BookRequestForDifficulty bookRequestForDifficulty) {
        Book book = bookRepository.findById(bookId).get();
        int totalDifficultyCount = book.getCountForDifficulty();
        int totalDifficultyScore = book.getDifficultyScore();

        if(bookRequestForDifficulty.getDifficulty().equals("hard")){
            totalDifficultyScore += 5;
            totalDifficultyCount += 1;
        } else if (bookRequestForDifficulty.getDifficulty().equals("normal")) {
            totalDifficultyScore += 3;
            totalDifficultyCount += 1;
        }else if(bookRequestForDifficulty.getDifficulty().equals("easy")){
            totalDifficultyScore += 1;
            totalDifficultyCount += 1;
        }else{
            throw new IllegalArgumentException("Invalid difficulty");
        }
        book.setDifficultyScore(totalDifficultyScore);
        book.setCountForDifficulty(totalDifficultyCount);
    }

    public List<BookDto> getBooksWithDifficulty(BookRequestForListUp bookRequestForListUp) {
        List<BookDto> bookDtoList = bookRepository.findByTitleContains(bookRequestForListUp.getKeyword()).stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }
}
