package com.example.demo.memo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public Long addMemo(MemoRequest memoRequest, String bookId) {
        Memo memo = Memo.from(memoRequest, bookId);
        memoRepository.save(memo);
        return memo.getMemoId();
    }

    public List<MemoDto> findMemosOfTheUser(Long bookID){
        List<Memo> memos = memoRepository.findAll();
        List<MemoDto> memoDtos = new ArrayList<>();
        for (Memo eachMemo : memos){
//            if (eachMemo.){
//                memoDtos.add(MemoDto.from(eachMemo));
//            }
            // 사용자 아이디 저장 문제 해결되면 이어서 진행하기
        }
        return memoDtos;
    }

    public void deleteMemo(Long bookID){
        memoRepository.deleteById(bookID);
    }
}
