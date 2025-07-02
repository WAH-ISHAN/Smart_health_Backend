package com.Smart_Health.Smart_Health_Backend.Logpage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("✅ Security config loaded without JWT!");

        http
                // Disable CSRF for stateless APIs
                .csrf(csrf -> csrf.disable())

                // Enable CORS with default settings (using our corsConfigurationSource bean)
                .cors(Customizer.withDefaults())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/login",
                                "/api/users/register",
                                "/api/users/register/admin",
                                "/api/feedback",
                                "/api/feedback/**",
                                "/api/doctor",
                                "/api/doctor/**",
                                "/api/hospitals",
                                "/api/hospitals/**",
                                "/api/hospitals/search",
                                "/api/appointment",
                                "/api/appointment/**",
                                "/reports",
                                "/reports/**"
                        ).permitAll()      // Public endpoints allowed without authentication
                        .anyRequest().permitAll() // All other requests also permitted (adjust later as needed)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // Provides the AuthenticationManager for any future authentication needs
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow only your React frontend to access this backend
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        // Allow standard HTTP methods for REST APIs
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allow any headers (you can customize if needed)
        config.setAllowedHeaders(Arrays.asList("*"));

        // Allow credentials such as cookies or authorization headers
        config.setAllowCredentials(true);

        // Map this CORS config to all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
