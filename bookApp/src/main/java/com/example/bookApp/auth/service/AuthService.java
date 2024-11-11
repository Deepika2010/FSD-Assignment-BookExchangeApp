package com.example.bookApp.auth.service;

import com.example.bookApp.auth.model.User;
import com.example.bookApp.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Authenticate user based on email and password
    public User authenticate(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be null or empty");
        }

        Optional<User> userOpt = userRepository.findByEmail(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt.get(); // Return the user if credentials are valid
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    // Save user (registration logic)
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
        return userRepository.save(user);
    }
    public boolean resetPassword(String recoveryEmail, String newPassword) {
        // Look for a user with the provided recovery email
        Optional<User> userOpt = userRepository.findByRecoveryEmail(recoveryEmail);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword)); // Hash the new password
            userRepository.save(user); // Save the updated user record
            return true;
        }
        return false; // User not found or email does not match
    }

    public void logout(HttpServletRequest request) {
        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
