package by.khodyko.different.securities.boot.db.dao;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
}
