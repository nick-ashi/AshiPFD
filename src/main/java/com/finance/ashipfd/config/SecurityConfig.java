package com.finance.ashipfd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
}
