package com.handsonhip.service;

import org.springframework.stereotype.Service;

import com.handsonhip.model.User;
import java.util.Map;
import java.util.HashMap;


@Service
public class UserService {
    //A simple in-memory user data structure
    private final Map<String, User> users = new HashMap<>();

    //User registration process
    public boolean register(User user){
        if(users.containsKey(user.getEmail())){
            return false; //User already exists
        }
        user.setPassword(hashPassword(user.getPassword()));
        users.put(user.getEmail(), user);
        return true;
    }

    //User login process
    public boolean login(String email, String password){
        User user = users.get(email);
        if(user != null){
            return verifyPassword(password, user.getPassword());
        }
        return false; //User not found
    }

    //User logout process
    //TO DO

    //A simple method to hash the password securely
    public String hashPassword(String password){
        return Integer.toHexString(password.hashCode());
    }

    //A simple method to verify password
    public boolean verifyPassword(String rawPassword, String hashedPassword){
        return hashPassword(rawPassword).equals(hashedPassword);
    }
}
