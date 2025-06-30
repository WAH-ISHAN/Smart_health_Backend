package com.Smart_Health.Smart_Health_Backend.Logpage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/users/login",
                                "/users/register",

                                // Feedback endpoints
                                "/api/feedback",
                                "/api/feedback/**",

                                // Doctor endpoints
                                "/doctor",
                                "/doctor/**",

                                // Hospital endpoints
                                "/api/hospitals",
                                "/api/hospitals/**",

                                // Report endpoints
                                "/reports",
                                "/reports/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
