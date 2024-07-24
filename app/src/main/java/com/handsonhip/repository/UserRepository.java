package com.handsonhip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.handsonhip.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}