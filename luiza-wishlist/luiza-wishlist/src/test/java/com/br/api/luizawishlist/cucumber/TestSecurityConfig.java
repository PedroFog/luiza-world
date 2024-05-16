package com.br.api.luizawishlist.cucumber;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    JwtDecoder jwtDecoder() {
        return token -> {
            throw new UnsupportedOperationException("Not implemented for tests yet");
        };
    }
}
