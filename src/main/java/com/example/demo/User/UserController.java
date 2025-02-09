package com.example.demo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add/{userEmail}")
    public ResponseEntity<UserResponse> addArticle(@PathVariable String userEmail) {
        UserDto articleDto = UserDto.from(userService.saveOrUpdate(userEmail));
        return ResponseEntity.ok().body(UserResponse.from(articleDto));
    }

    @PutMapping("/{userEmail}")
    public ResponseEntity<UserResponse> updateArticle(@PathVariable String userEmail, @RequestBody String nickname) {
        UserDto userDto = UserDto.from(userService.update(userEmail, nickname));
        return ResponseEntity.ok().body(UserResponse.from(userDto));
    }
}
