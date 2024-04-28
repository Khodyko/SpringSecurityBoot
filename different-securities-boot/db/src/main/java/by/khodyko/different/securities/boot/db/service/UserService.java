package by.khodyko.different.securities.boot.db.service;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import by.khodyko.different.securities.boot.db.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Boolean isAttemptsLoginAvailable(LoginAttempt loginAttempt);

    Optional<User> getUserByUserName(String userName);

    void setLoginAttemptsZero(String userName);

    void increaseLoginAttempts(String userName);

    User createUser(User user);

}
