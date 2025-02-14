package com.example.demo.User.Application;

import com.example.demo.User.Domain.User;
import com.example.demo.User.Domain.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
   private final HttpSession session;

    public User saveOrUpdate(String email, String name) {
        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {
            return existingUser;
        }else{
            User user = new User();
            user.setEmail(email);
            user.setNickname(name);
            return userRepository.save(user);
        }
    }

    public String getUserEmail() {
        String email = (String) session.getAttribute("email");
        return email;
    }
}
