package com.example.demo.User;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import jakarta.servlet.http.HttpSession;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/api/auth")
public class LoginSessionController {

    @Value("${google.oauth.client-id}")
    private String clientId;

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
                String name = (String) payload.get("name");

                // 세션 객체에 사용자 정보 저장
                session.setAttribute("email", email);
                session.setAttribute("name", name);

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
        String name = (String) session.getAttribute("name");

        if (email != null) {
            return ResponseEntity.ok(Map.of(
                    "email", email,
                    "name", name
            ));
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "User not logged in"));
        }
    }
}
