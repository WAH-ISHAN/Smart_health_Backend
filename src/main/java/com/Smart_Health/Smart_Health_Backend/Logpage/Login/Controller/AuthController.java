package com.Smart_Health.Smart_Health_Backend.Logpage.Login.Controller;

import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Enitiy.User;
import com.Smart_Health.Smart_Health_Backend.Logpage.Login.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")  // React dev server URL
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String rawPassword = loginData.get("password");
        String role = loginData.get("role");

        Map<String, String> response = new HashMap<>();
        User foundUser = userService.findByEmail(email);

        if (foundUser != null &&
                userService.checkPassword(rawPassword, foundUser.getPassword()) &&
                foundUser.getRole().equalsIgnoreCase(role)) {

            response.put("message", "Login successful!");
            response.put("role", foundUser.getRole());
            response.put("email", foundUser.getEmail());
            response.put("username", foundUser.getUsername());

        } else {
            response.put("message", "Invalid email, password or role!");
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

        user.setRole("user");  // default role
        userService.registerUser(user);
        response.put("message", "Registration successful!");
        return response;
    }

    @PostMapping("/register/admin")
    public Map<String, String> registerAdminOrDoctor(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (userService.findByEmail(user.getEmail()) != null) {
            response.put("message", "Email already exists!");
            return response;
        }

        String role = user.getRole();

        if (role == null ||
                !(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("doctor"))) {
            response.put("message", "Role must be 'admin' or 'doctor' for this endpoint.");
            return response;
        }

        userService.registerUser(user);
        response.put("message", "Registration successful for " + role + "!");
        return response;
    }
}
