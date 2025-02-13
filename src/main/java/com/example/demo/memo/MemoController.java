package com.example.demo.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/add/{bookId}")
    public ResponseEntity<MemoResponseAboutMemoId> addMemo(@RequestBody MemoRequest memoRequest, @PathVariable Long bookId) {
        Long memoId = memoService.addMemo(memoRequest, bookId);
        String message = "메모 저장 성공!";
        return ResponseEntity.ok(MemoResponseAboutMemoId.from(memoId, message));
    }

    @GetMapping("/list/{bookId}")
    public ResponseEntity<List<MemoResponse>> findMemosOfTheUser(@PathVariable Long bookId) {
        List<MemoDto> memoDtos = memoService.findMemosOfTheUser(bookId);
        return ResponseEntity.ok(memoDtos.stream().map(MemoResponse::from).toList());
    }

    @DeleteMapping("/delete/{memoId}")
    public ResponseEntity<String> deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
        String message = "메모 삭제 성공!";
        return ResponseEntity.ok(message);
    }
}
