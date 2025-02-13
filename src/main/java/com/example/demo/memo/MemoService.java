package com.example.demo.memo;

import com.example.demo.Book.Book;
import com.example.demo.Book.BookRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemoService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final MemoRepository memoRepository;

    public Long addMemo(MemoRequest memoRequest, Long bookId) {
        String userEmail = userService.getUserEmail();
        User user = userRepository.findByEmail(userEmail);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        Book book = bookRepository.findById(bookId).get();
        if(book == null) {
            throw new RuntimeException("Book not found");
        }
        Memo memo = Memo.createMemo(memoRequest, user, book);
        memoRepository.save(memo);
        return memo.getId();
    }

    public List<MemoDto> findMemosOfTheUser(Long bookId){
        Book book = bookRepository.findById(bookId).get();
        User user = userRepository.findByEmail(userService.getUserEmail());

        List<MemoDto> memoDtos = memoRepository.findByBookAndUser(book, user).stream().map(MemoDto::from).collect(Collectors.toList());
        return memoDtos;
    }

    public void deleteMemo(Long memoId){
        memoRepository.deleteById(memoId);
    }
}
