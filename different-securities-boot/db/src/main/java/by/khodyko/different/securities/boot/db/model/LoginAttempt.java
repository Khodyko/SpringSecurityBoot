package by.khodyko.different.securities.boot.db.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "failed_login_attempt", nullable = false, columnDefinition = "integer DEFAULT 0")
    private int failedLoginAttempts = 0;
    @Column
    private LocalDateTime lastFailedLoginTime=LocalDateTime.now();


    public LoginAttempt(){

    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void increaseFailedLoginAttempts() {
        failedLoginAttempts++;
    }

    public void resetFailedLoginAttempts() {
        failedLoginAttempts = 0;
    }

    public LocalDateTime getLastFailedLoginTime() {
        return lastFailedLoginTime;
    }

    public void updateLastFailedLoginTime() {
        lastFailedLoginTime = LocalDateTime.now();
    }

    public void setLastFailedLoginTime(LocalDateTime lastFailedLoginTime) {
        this.lastFailedLoginTime = lastFailedLoginTime;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
