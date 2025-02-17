package com.example.demo.memo.Presentation;

import com.example.demo.memo.Application.MemoDto;
import com.example.demo.memo.Application.MemoService;
import com.example.demo.memo.Presentation.Request.MemoRequest;
import com.example.demo.memo.Presentation.Response.MemoResponse;
import com.example.demo.memo.Presentation.Response.MemoResponseAboutMemoId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/add/{isbn}")
    public ResponseEntity<MemoResponseAboutMemoId> addMemo(@RequestBody MemoRequest memoRequest, @PathVariable String isbn) {
        Long memoId = memoService.addMemo(memoRequest, isbn);
        String message = "메모 저장 성공!";
        return ResponseEntity.ok(MemoResponseAboutMemoId.from(memoId, message));
    }

    @GetMapping("/list/{isbn}")
    public ResponseEntity<List<MemoResponse>> findMemosOfTheUser(@PathVariable String isbn, @RequestParam String userEmail) {
        List<MemoDto> memoDtos = memoService.findMemosOfTheUser(isbn, userEmail);
        return ResponseEntity.ok(memoDtos.stream().map(MemoResponse::from).toList());
    }

    @DeleteMapping("/delete/{memoId}")
    public ResponseEntity<String> deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
        String message = "메모 삭제 성공!";
        return ResponseEntity.ok(message);
    }
}
