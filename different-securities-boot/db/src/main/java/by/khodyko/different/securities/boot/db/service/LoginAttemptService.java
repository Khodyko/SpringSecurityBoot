package by.khodyko.different.securities.boot.db.service;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;

public interface LoginAttemptService {

    public LoginAttempt save(LoginAttempt loginAttempt);

    public LoginAttempt getLoginAttemptByUserName(String userName);
}
