package com.example.demo.memo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;
    private String bookId;
    private String content;
    private String date;

    public static Memo from(MemoRequest memoRequest, String id) {
        return  Memo.builder()
                .bookId(id)
                .content(memoRequest.getContent())
                .date(memoRequest.getDate())
                .build();
    }

}
