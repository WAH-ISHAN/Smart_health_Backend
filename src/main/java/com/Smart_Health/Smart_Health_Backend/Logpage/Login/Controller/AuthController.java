package com.Smart_Health.Smart_Health_Backend.Logpage.Login.Controller;

import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Enitiy.User;
import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173") // Set this to your React dev server port
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        User foundUser = userService.findByEmail(user.getEmail());
        Map<String, String> response = new HashMap<>();

        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            response.put("message", "Login successful!");
            response.put("role", foundUser.getRole());
        } else {
            response.put("message", "Invalid email or password!");
        }

        return response;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (userService.findByEmail(user.getEmail()) != null) {
            response.put("message", "Email already exists!");
            return response;
        }

        user.setRole("user"); // default role
        userService.registerUser(user);
        response.put("message", "Registration successful!");
        return response;
    }
}
