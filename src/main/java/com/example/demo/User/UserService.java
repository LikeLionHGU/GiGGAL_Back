package com.example.demo.User;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdate(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return existingUser.get();
        }else{
            User user = new User();
            user.setEmail(email);
            return userRepository.save(user);
        }
    }

    @Transactional
    public User update(String email, String Nickname){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        user.setNickname(Nickname);
        return user;
    }
}
