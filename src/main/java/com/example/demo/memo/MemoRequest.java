package com.example.demo.memo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
public class MemoRequest {
    private String content;
    private String date;
}
