package com.example.demo.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    String nickname;

    public static UserResponse from(UserDto userDto) {
        return UserResponse.builder()
                .nickname(userDto.getNickname())
                .build();
    }
}
