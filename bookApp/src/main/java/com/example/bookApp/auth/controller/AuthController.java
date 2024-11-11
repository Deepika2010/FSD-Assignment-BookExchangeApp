package com.example.bookApp.auth.controller;

import com.example.bookApp.auth.model.User;
import com.example.bookApp.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User request) {
        if (request.getUsername() == null || request.getPassword() == null ||
                request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be null or empty");
        }

        User user = authService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    // Register user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null ||
                user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be null or empty");
        }

        authService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

    // Endpoint to reset the password using the reset token
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody User request) {
        String recoveryEmail = request.getRecoveryEmail();
        String newPassword = request.getNewPassword();

        // Call service to reset password
        if (authService.resetPassword(recoveryEmail, newPassword)) {
            return ResponseEntity.ok("Password has been reset successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Recovery email not found.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);  // Call logout service method
        return ResponseEntity.ok("Logout successful");
    }
}