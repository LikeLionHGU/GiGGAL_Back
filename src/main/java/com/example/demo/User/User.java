package com.example.demo.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
public class User {

    @Id
    private String email;
    private String nickname;

    private User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static User of(String email, String nickname) {
        return new User(email, nickname);
    }

}
