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

@Service
@AllArgsConstructor
public class MemoService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final MemoRepository memoRepository;

    public Long addMemo(MemoRequest memoRequest, String bookId) {
        String userEmail = userService.getUserEmail();
        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId);
        if(book == null) {
            throw new RuntimeException("Book not found");
        }
        Memo memo = Memo.createMemo(memoRequest, user, book);
        memoRepository.save(memo);
        return memo.getMemoId();
    }

    public List<MemoDto> findMemosOfTheUser(String bookId){
        List<Memo> memos = memoRepository.findAll();
        List<MemoDto> memoDtos = new ArrayList<>();

        for (Memo eachMemo : memos){
            if (eachMemo.getBook().getId().equals(bookId) && eachMemo.getUser().getEmail().equals(userService.getUserEmail())){
                String bookTitle = bookRepository.findById(bookId).getTitle();
                memoDtos.add(MemoDto.from(eachMemo, bookTitle));
            }
        }
        return memoDtos;
    }

    public void deleteMemo(Long memoId){
        memoRepository.deleteById(memoId);
    }
}
