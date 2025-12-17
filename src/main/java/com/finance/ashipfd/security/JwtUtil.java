package com.finance.ashipfd.security;

import org.springframework.stereotype.Component;

/**
 * JWTUTIL CLASS:
 *
 * - Generate JWT token when user logs in
 * - Sign tokens so they can't be forged
 * - Will be used later to verify / decode tokens
 *
 * @Component = BEANIFY it to inject into services
 *
 */
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "392d95804762a8d60c21dd8704d9299685cf24bb9ba291f43567488c5065bf51";
    private static final long EXPIRATION_TIME = 86400000;
}
