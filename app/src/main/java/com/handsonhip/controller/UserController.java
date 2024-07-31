package com.handsonhip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.handsonhip.model.User;
import com.handsonhip.service.UserService;

@RestController
@RequestMapping("/api/member")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.register(user)) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(400).body("User already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String sessionId = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (sessionId != null) {
            return ResponseEntity.ok(sessionId);
        } else {
            return ResponseEntity.status(400).body("Invalid email or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody SessionRequest sessionRequest) {
        userService.logout(sessionRequest.getSessionId());
        return ResponseEntity.ok("Logout successful.");
    }

    @PostMapping("/status")
    public ResponseEntity<String> status(@RequestBody SessionRequest sessionRequest) {
        if (userService.isUserLoggedIn(sessionRequest.getSessionId())) {
            return ResponseEntity.ok("User is logged in.");
        } else {
            return ResponseEntity.ok("User is logged out.");
        }
    }
    // Inner class to handle login request
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    // Inner class to handle session requests
    public static class SessionRequest {
        private Long sessionId;

        public Long getSessionId() {
            return sessionId;
        }

        public void setSessionId(Long sessionId) {
            this.sessionId = sessionId;
        }
    }
}
