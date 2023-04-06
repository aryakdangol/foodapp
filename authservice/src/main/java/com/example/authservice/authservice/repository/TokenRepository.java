package com.example.authservice.authservice.repository;

import java.util.Optional;

public interface TokenRepository {

    public Optional<String> getToken(String key);

    public void saveToken(String key, String token);

    public void deleteToken(String key);





}
