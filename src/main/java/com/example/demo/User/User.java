package com.example.demo.User;

import com.example.demo.BookMark.BookMark;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String email;
    private String nickname;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BookMark> bookMarks = new ArrayList<>();

}
