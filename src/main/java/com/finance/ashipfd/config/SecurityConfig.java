package com.finance.ashipfd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// tells spring this class contains conf
// spring scns this and will process the @Bean methods
@Configuration
// Enable sec for the app
// Enables security filters
// Sets up security filter chain
// Activates security annotations
@EnableWebSecurity
public class SecurityConfig {

    // Tells spring to manage this object as a Spring Bean (needs this filter chain to wokr)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    /**
     * NOTES:
     * BCRYPT --> password hashing func
     *  - Purposely SLOW so people can't brute force it as easily
     * @return BCryptPasswordEncoder()
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
