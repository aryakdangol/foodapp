package com.example.authservice.authservice.repository.impl;

import com.example.authservice.authservice.repository.TokenRepository;
import io.dapr.client.DaprClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;

@AllArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    @Lazy
    DaprClient client;

    @Override
    public String getToken(String key) {
        return null;
    }

    @Override
    public String saveToken(String token) {
        return null;
    }
}
