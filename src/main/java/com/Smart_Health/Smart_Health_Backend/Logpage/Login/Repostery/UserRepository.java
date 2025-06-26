package com.Smart_Health.Smart_Health_Backend.Logpage.Login.Repostery;

import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Enitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
