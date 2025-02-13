package com.example.demo.User;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
