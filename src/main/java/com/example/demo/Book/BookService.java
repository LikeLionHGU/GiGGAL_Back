package com.example.demo.Book;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void createBook(BookRequest bookRequest) {
        Book book = bookRepository.findByTitleAndAuthorAndPublisher(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublisher());
        if(book == null) {
           BookDto bookDto = BookDto.from(bookRepository.save(Book.from(bookRequest)));
        }else{
            updateBookMarkCount(book);
        }
    }

    @Transactional
    public void updateBookMarkCount(Book book){
        int originalCountOfBookMark = book.getCountOfBookMark();
        book.setCountOfBookMark(originalCountOfBookMark + 1);
    }

    public List<BookDto> getBooksWithBookMarkCount(BookRequestForListUp bookRequestForListUp) {
        List<BookDto> bookDtoList = bookRepository.findAll().stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }

    @Transactional
    public void editBookDifficulty(Long bookId, BookRequestForDifficulty bookRequestForDifficulty) {
        Book book = bookRepository.findById(bookId).get();
        int originalDifficultyCount = book.getCountForDifficulty();
        int originalDifficultyScore = book.getDifficultyScore();
        if(bookRequestForDifficulty.getDifficulty().equals("hard")){
            book.setCountForDifficulty(originalDifficultyCount + 1);
            book.setCountForDifficulty(originalDifficultyScore + 5);
        } else if (bookRequestForDifficulty.getDifficulty().equals("normal")) {
            book.setCountForDifficulty(originalDifficultyCount + 1);
            book.setCountForDifficulty(originalDifficultyScore + 3);
        }else if(bookRequestForDifficulty.getDifficulty().equals("easy")){
            book.setCountForDifficulty(originalDifficultyCount + 1);
            book.setCountForDifficulty(originalDifficultyScore + 1);
        }else{
            throw new IllegalArgumentException("Invalid difficulty");
        }
    }

    public List<BookDto> getBooksWithDifficulty(BookRequestForListUp bookRequestForListUp) {
        List<BookDto> bookDtoList = bookRepository.findByTitleContains(bookRequestForListUp.getKeyword()).stream().map(BookDto::from).collect(Collectors.toList());
        return bookDtoList;
    }
}
