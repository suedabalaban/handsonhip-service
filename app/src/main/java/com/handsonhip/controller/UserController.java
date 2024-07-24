package com.handsonhip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.handsonhip.model.User;
import com.handsonhip.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean success = userService.register(user);
        if(success){
            return ResponseEntity.ok("User registered successfully.");
        }else{
            return ResponseEntity.badRequest().body("User already exists.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean success = userService.login(email, password);
        if(success){
            return ResponseEntity.ok("Login successful.");
        }else{
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }
    }
    
    //TO DO LOGOUT
    
}
