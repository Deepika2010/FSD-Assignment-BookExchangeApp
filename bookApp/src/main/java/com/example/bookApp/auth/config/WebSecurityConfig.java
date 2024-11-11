//package com.example.bookApp.auth.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/recover-password").permitAll() // Public endpoints
//                        .anyRequest().authenticated() // Secure other endpoints
//                )
//                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (only do this if you're not using it)
//
//        return http.build();
//    }
//}
