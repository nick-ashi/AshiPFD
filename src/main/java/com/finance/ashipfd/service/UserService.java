package com.finance.ashipfd.service;

import com.finance.ashipfd.dto.RegisterRequest;
import com.finance.ashipfd.exception.EmailAlreadyExistsException;
import com.finance.ashipfd.repository.UserRepository;
import com.finance.ashipfd.model.User;
import jakarta.validation.constraints.Email;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Service layer for User-related business logic
 *
 * Separate concerns
 *  - Controllers: Handle the HTTP reqs, send responses
 *  - Services: Handle business logic (validation, transformation, orchestration)
 *  - Repos: Database ops only
 */
@Service //Service BEAN, tells spring basically to manage for me
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * CONSTRUCTOR FOR INJECTION
     *  - Will find the beans and pass them in automatically
     * @param userRepository
     * @param passwordEncoder
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register new user
     *
     * 1. Check if email alr exists
     * 2. Hash password
     * 3. Create and save user
     * 4. Return saved user
     * @param request DTO containing reg data from client
     * @return newly created user entity
     * @throws RuntimeException if email alr exists
     */
    public User registerUser(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Create new user entity
        User user = new User();
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
