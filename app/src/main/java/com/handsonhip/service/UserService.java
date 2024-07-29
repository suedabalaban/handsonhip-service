package com.handsonhip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handsonhip.model.User;
import com.handsonhip.model.Session;
import com.handsonhip.repository.UserRepository;
import com.handsonhip.repository.SessionRepository;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    //User registration process
    public boolean register(User user){
        if (userRepository.findByEmail(user.getEmail()) != null){
            return false; //User already exists
        }
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    //User login process
    public String login(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user != null && verifyPassword(password, user.getPassword())){
            String sessionId = UUID.randomUUID().toString();
            Session session = new Session(user, sessionId);
            sessionRepository.save(session);
            return sessionId;
        }
        return null; //User not found or invalid password
    }

    //User logout process
    public void logout(String sessionId){
        sessionRepository.deleteBySessionId(sessionId);
    }

    //Check whether user is logged in or not
    public boolean isUserLoggedIn(String sessionId){
        return sessionRepository.findBySessionId(sessionId) != null;
    }

    //A simple method to hash the password securely
    public String hashPassword(String password){
        return Integer.toHexString(password.hashCode());
    }

    //A simple method to verify password
    public boolean verifyPassword(String rawPassword, String hashedPassword){
        return hashPassword(rawPassword).equals(hashedPassword);
    }
}
