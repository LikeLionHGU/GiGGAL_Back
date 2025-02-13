package com.example.demo.memo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemoResponseAboutMemoId {
    private Long id;
    private String message;

    public static MemoResponseAboutMemoId from(Long id, String successMessage) {
        return MemoResponseAboutMemoId.builder()
                .id(id)
                .message(successMessage)
                .build();
    }

}
