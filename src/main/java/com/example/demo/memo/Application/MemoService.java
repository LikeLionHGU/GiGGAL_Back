package com.example.demo.memo.Application;

import com.example.demo.Book.Domain.Book;
import com.example.demo.Book.Domain.BookRepository;
import com.example.demo.User.Domain.User;
import com.example.demo.User.Domain.UserRepository;
import com.example.demo.User.Application.UserService;
import com.example.demo.memo.Domain.Memo;
import com.example.demo.memo.Domain.MemoRepository;
import com.example.demo.memo.Presentation.Request.MemoRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemoService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final MemoRepository memoRepository;

    public Long addMemo(MemoRequest memoRequest, String googleBookId) {
        String userEmail = memoRequest.getUserEmail();
        User user = userRepository.findByEmail(userEmail);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        Book book = bookRepository.findByGoogleBookId(googleBookId);
        if(book == null) {
            throw new RuntimeException("Book not found");
        }
        Memo memo = Memo.createMemo(memoRequest, user, book);
        memoRepository.save(memo);
        return memo.getId();
    }

    public List<MemoDto> findMemosOfTheUser(String googleBookId, String userEmail) {
        Book book = bookRepository.findByGoogleBookId(googleBookId);
        User user = userRepository.findByEmail(userEmail);

        List<MemoDto> memoDtos = memoRepository.findByBookAndUser(book, user).stream().map(MemoDto::from).collect(Collectors.toList());
        return memoDtos;
    }

    public void deleteMemo(Long memoId){
        memoRepository.deleteById(memoId);
    }
}
