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
                                "/api/feedback",            // Allow GET
                                "/api/feedback/**",         // Allow POST/GET/DELETE by ID etc.

                                //DOCTOR END POINTS
                                "/api/doctor", // Get all doctors & create doctor
                                "/api/doctor/{id}", // Get doctor by id
                                "/api/doctor/hospital/{hospital}", // Get doctor by hospital
                                "/api/doctor/speciality/{speciality}", // Get doctor by speciality
                                "/api/doctor/status/{status}", // Get doctor by status
                                "/api/doctor/{id}", //delete doctor by id

                                //APPOINTMENTS END POINTS
                                "/api/appointment", // Get all appointments & create
                                "/api/appointment/{id}", // Get,put, delete doctor by id
                                "/api/appointment/user/{userId}" // GET /api/appointments/user/{userId}



                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
