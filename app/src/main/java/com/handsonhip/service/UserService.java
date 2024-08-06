package com.handsonhip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.handsonhip.model.User;
import com.handsonhip.model.Session;
import com.handsonhip.repository.UserRepository;
import com.handsonhip.repository.SessionRepository;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    //Define non-mutable salt length  
    private static final int SALT_LENGTH = 16;

    // Method to generate a random salt
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Method to hash the password with the salt
    private String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password + salt, BCrypt.gensalt());
    }
    
    // User registration process
    public boolean register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false; // User already exists
        }
        String salt = generateSalt();
        String hashedPassword = hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        userRepository.save(user);
        return true;
    }

    //User login process
    public String login(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user != null && verifyPassword(password, user.getPassword(), user.getSalt())){
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

    // Method to verify password
    private boolean verifyPassword(String rawPassword, String hashedPassword, String salt) {
        return BCrypt.checkpw(rawPassword + salt, hashedPassword);
    }
}
