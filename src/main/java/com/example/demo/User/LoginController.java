package com.example.demo.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Value("${google.oauth.client-id}")
    private String clientId;

    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/google")
    public ResponseEntity<Map<String, Object>> google(@RequestParam String credential) {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            // ID Token이 같은지 유효성 검사 :: https://jwt.io/ 에서 확인 가능
            GoogleIdToken idToken = verifier.verify(credential);
            if (idToken != null) {

                // Payload는 ID Token에 포함된 사용자 정보를 나타냄.(GoogleIdToken에 ID Token이 포함되어 있음.)
                // -> 여기에서 사용자 정보를 확인하고 저장
                Payload payload = idToken.getPayload();


                String email = payload.getEmail();
                String name = (String) payload.get("name");

                userService.saveOrUpdate(email, name);    // 사용자 정보 확인 또는 저장
                // 응답 데이터 생성
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("email", email);
                response.put("name", name);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid ID token."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Server error occurred."));
        }
    }
}
