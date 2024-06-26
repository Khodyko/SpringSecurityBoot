package by.khodyko.different.securities.boot.db.model;

import by.khodyko.different.securities.boot.db.enums.Role;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "login_attempts_id", referencedColumnName = "id")
    private LoginAttempt loginAttempt =new LoginAttempt();

    public User() {
        // Пустой конструктор (обязателен для JPA)
    }

    public User(Long id, String username, String password, boolean enabled, Role role, LoginAttempt loginAttempt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.loginAttempt = loginAttempt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LoginAttempt getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(LoginAttempt loginAttempt) {
        this.loginAttempt = Objects.requireNonNullElseGet(loginAttempt, LoginAttempt::new);
    }
}