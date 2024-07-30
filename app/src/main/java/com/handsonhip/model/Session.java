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
    @JoinColumn(name = "memberID", nullable = false)
    private User user;

    @Column(name = "logintime", nullable = false)
    private LocalDateTime loginTime;

    //No-arg constructor
    public Session() {}

    //All-args constructor
    public Session(User user) {
        this.user = user;
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

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
