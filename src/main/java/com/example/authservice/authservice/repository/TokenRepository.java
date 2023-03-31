package com.example.authservice.authservice.repository;

import org.apache.catalina.User;

public interface TokenRepository {

    public String getToken(String key);

    public String saveToken(String token);





}
