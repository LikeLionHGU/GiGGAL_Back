package com.example.demo.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdate(String email, String nickname) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setNickname(nickname);
            return userRepository.save(user);
        }else{
            User user = new User();
            user.setEmail(email);
            user.setNickname(nickname);
            return userRepository.save(user);
        }
    }

}
