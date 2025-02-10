package com.example.demo.User;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PutMapping("/{userEmail}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userEmail, @RequestBody String nickname) {
        UserDto userDto = UserDto.from(userService.update(userEmail, nickname));
        return ResponseEntity.ok().body(UserResponse.from(userDto));
    }
}
