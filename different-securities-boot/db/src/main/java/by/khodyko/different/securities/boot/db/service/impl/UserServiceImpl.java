package by.khodyko.different.securities.boot.db.service.impl;

import by.khodyko.different.securities.boot.db.dao.UserRepository;
import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import by.khodyko.different.securities.boot.db.model.User;
import by.khodyko.different.securities.boot.db.service.LoginAttemptService;
import by.khodyko.different.securities.boot.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private LoginAttemptService loginAttemptService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoginAttemptService loginAttemptService) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (this.isAttemptsLoginAvailable(user.getLoginAttempt())) {
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole()));
            }
        }
        throw new UsernameNotFoundException("Can't find user by name:" + username);
    }

    @Override
    public Boolean isAttemptsLoginAvailable(LoginAttempt loginAttempt) {
        if (loginAttempt != null) {
            LocalDateTime lastAttemptTime = loginAttempt.getLastFailedLoginTime().plusMinutes(20);
            Boolean isTimeAttemptPassed = lastAttemptTime.isBefore(LocalDateTime.now());
            Boolean isAttemptsAvailable = loginAttempt.getFailedLoginAttempts() < 3;
            return isTimeAttemptPassed || isAttemptsAvailable;
        } else {
            return true;
        }
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {

        return userRepository.findByUsername(userName);
    }

    @Override
    public void setLoginAttemptsZero(String userName) {
        Optional<User> userOpt = userRepository.findByUsername(userName);
        userOpt.ifPresent(user -> {
            user.getLoginAttempt().resetFailedLoginAttempts();
            userRepository.save(user);
        });
    }

    @Override
    public void increaseLoginAttempts(String userName) {
        Optional<User> userOpt = userRepository.findByUsername(userName);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.getLoginAttempt().increaseFailedLoginAttempts();
            user.getLoginAttempt().updateLastFailedLoginTime();
            userRepository.save(user);
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

}