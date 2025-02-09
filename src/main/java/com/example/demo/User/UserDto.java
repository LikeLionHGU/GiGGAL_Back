package com.example.demo.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto {
    private String nickname;

    public static UserDto from(User user) {
        return UserDto.builder()
                .nickname(user.getNickname())
                .build();
    }
}
