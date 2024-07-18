package by.khodyko.different.securities.boot.db.service.impl;

import by.khodyko.different.securities.boot.db.dao.LoginAttemptRepository;
import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import by.khodyko.different.securities.boot.db.service.LoginAttemptService;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private LoginAttemptRepository loginAttemptRepository;

    public LoginAttemptServiceImpl(LoginAttemptRepository loginAttemptRepository) {
        this.loginAttemptRepository = loginAttemptRepository;
    }

    @Override
    public LoginAttempt save(LoginAttempt loginAttempt) {
        return loginAttemptRepository.save(loginAttempt);
    }

    @Override
    public LoginAttempt getLoginAttemptByUserName(String userName) {
        return loginAttemptRepository.getLoginAttemptByUserName(userName);
    }

}
