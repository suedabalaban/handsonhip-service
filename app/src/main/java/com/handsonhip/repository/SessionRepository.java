package com.handsonhip.repository;

import com.handsonhip.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}

