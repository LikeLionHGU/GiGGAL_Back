package com.example.demo.memo.Presentation.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemoRequest {

    private String userEmail;
    private String content;
    private String date;
}
