package com.finance.ashipfd.service;

import com.finance.ashipfd.dto.RegisterRequest;
import com.finance.ashipfd.repository.UserRepository;
import com.finance.ashipfd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
     * CONSTRUCTOR INJECTION
     *  - Will find the beans and pass them in automatically
     * @param userRepository
     * @param passwordEncoder
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
