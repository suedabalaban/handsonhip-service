package com.handsonhip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.handsonhip.model.User;
import com.handsonhip.service.UserService;

@RestController
@RequestMapping("/users")
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
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        String sessionId = userService.login(email, password);
        if(sessionId != null){
            return ResponseEntity.ok("sessionId");
        }else{
            return ResponseEntity.status(400).body("Invalid email or password");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String sessionId) {
        userService.logout(sessionId);
        return ResponseEntity.ok("Logout successful.");
    }
    
    @GetMapping("/status")
    public ResponseEntity<String> status(@RequestParam String email){
        if(userService.isUserLoggedIn(email)){
            return ResponseEntity.ok("User is logged in.");
        }else{
            return ResponseEntity.ok("User is logged out.");
        }
    }
}
