package com.handsonhip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.handsonhip.model.User;
import com.handsonhip.model.Session;
import com.handsonhip.repository.UserRepository;
import com.handsonhip.repository.SessionRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;


       // Method to hash the password with BCrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    // Registration process
    public boolean register(User user) {
    if (userRepository.findByEmail(user.getEmail()) != null) {
        return false; // User already exists
    }
    String hashedPassword = hashPassword(user.getPassword());
    user.setPassword(hashedPassword);
    userRepository.save(user);
    return true;
    }

    // Login process
    public String login(String email, String password){
    User user = userRepository.findByEmail(email);
    if(user != null && verifyPassword(password, user.getPassword())){
        // Create a new session without session_id
        Session session = new Session(user);
        sessionRepository.save(session);
        // Return the session ID
        return session.getId().toString();
    }
    return null; // User not found or invalid password
    }

    //User logout process
    public void logout(Long sessionId){
        sessionRepository.deleteById(sessionId);
    }

    //Check whether user is logged in or not
    public boolean isUserLoggedIn(Long sessionId){
        return sessionRepository.findById(sessionId).isPresent();
    }

    // Password verification
    private boolean verifyPassword(String rawPassword, String hashedPassword) {
    return BCrypt.checkpw(rawPassword, hashedPassword);
}
}
