package com.Smart_Health.Smart_Health_Backend.Logpage.Login.Services;

import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Enitiy.User;
import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Repostery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
