package com.handsonhip.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {
    //Table attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    //No-arg constructor
    public Session() {}

    //All-args constructor
    public Session(User user, String sessionId) {
        this.user = user;
        this.sessionId = sessionId;
    }

    @PrePersist
    public void onPrePersist() {
        if (this.loginTime == null) {
            this.loginTime = LocalDateTime.now();
        }
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
