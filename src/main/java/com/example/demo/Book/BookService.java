package com.example.demo.Book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void createBook(BookRequest bookRequest) {
        Book book = bookRepository.findByTitleAndAuthorAndPublisher(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublisher());
        if(book == null) {
           BookDto bookDto = BookDto.from(bookRepository.save(Book.from(bookRequest)));
        }
    }

}
