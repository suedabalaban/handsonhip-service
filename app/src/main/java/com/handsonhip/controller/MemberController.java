package com.handsonhip.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.handsonhip.model.Member;
import com.handsonhip.service.MemberService;
import com.handsonhip.service.JwtService;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member user) {
        if (memberService.register(user)) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = memberService.loadUserByUsername(loginRequest.getEmail());

        if (userDetails != null && passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok("Logout successful.");
    }

    @GetMapping("/status")
    public ResponseEntity<String> status(@RequestHeader("Authorization") String token) {
        try {
            boolean isValid = jwtService.validateToken(token.replace("Bearer ", ""), null); // UserDetails null olabilir
            return isValid ? ResponseEntity.ok("User is logged in.") : ResponseEntity.ok("User is logged out.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }

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
}
