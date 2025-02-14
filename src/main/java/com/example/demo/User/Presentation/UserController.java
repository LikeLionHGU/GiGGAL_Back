package com.example.demo.User.Presentation;

import com.example.demo.User.Application.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    @Value("${google.oauth.client-id}")
    private String clientId;

    private final UserService userService;

    @PostMapping("/google/session")
    public ResponseEntity<Map<String, Object>> googleLogin(@RequestParam String credential, HttpSession session) {
        // ID Token 검증 및 사용자 정보 추출
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(credential);
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                int index = email.indexOf("@");
                String nickName = email.substring(0, index);

                userService.saveOrUpdate(email, nickName);

                // 세션 객체에 사용자 정보 저장
                session.setAttribute("email", email);
                session.setAttribute("nickName", nickName);

                return ResponseEntity.ok(Collections.singletonMap("status", "success"));
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid ID token"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Server error occurred"));
        }
    }

    @GetMapping("/user/session")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpSession session) {
        String email = (String) session.getAttribute("email");
        String nickName = (String) session.getAttribute("nickName");

        if (email != null) {
            return ResponseEntity.ok(Map.of(
                    "email", email,
                    "nickName", nickName
            ));
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "User not logged in"));
        }
    }

    @PutMapping("/user/session/out")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        session.setAttribute("email", null);
        session.setAttribute("nickName", null);
        return ResponseEntity.ok(Collections.singletonMap("status", "logout"));
    }
}
