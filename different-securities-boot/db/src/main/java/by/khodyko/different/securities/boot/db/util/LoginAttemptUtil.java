package by.khodyko.different.securities.boot.db.util;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;

import java.time.LocalDateTime;

public class LoginAttemptUtil {
    public static int MAX_ATTEMPT_COUNT=3;
    public static int ATTEMPT_WAITING_TIME_MINUTES=20;
    public static Boolean isAttemptBlocked(LoginAttempt loginAttempt) {
        if (loginAttempt != null) {
            Boolean isTimeAttemptPassed = isTimePastAfterLastAttempt(loginAttempt);
            boolean isAttemptsAvailable = loginAttempt.getFailedLoginAttempts() < MAX_ATTEMPT_COUNT;
            return !isTimeAttemptPassed && !isAttemptsAvailable;
        } else {
            return true;
        }
    }


    public static Boolean isTimePastAfterLastAttempt(LoginAttempt loginAttempt){
        LocalDateTime lastAttemptTime = loginAttempt.getLastFailedLoginTime().plusMinutes(ATTEMPT_WAITING_TIME_MINUTES);
       return lastAttemptTime.isBefore(LocalDateTime.now());
    }
}
