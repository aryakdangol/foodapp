package com.example.authservice.authservice.repository.impl;

import com.example.authservice.authservice.repository.TokenRepository;
import com.example.authservice.authservice.util.AppConstants;
import io.dapr.client.DaprClient;
import io.dapr.exceptions.DaprException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TokenRepositoryImpl implements TokenRepository {

    Logger logger = LoggerFactory.getLogger(TokenRepositoryImpl.class);
    @Lazy
    @Autowired
    DaprClient client;

    @Override
    public String getToken(String key) {
       return null;
    }

    @Override
    public void saveToken(String key, String token) {
        try{
            client.saveState(AppConstants.STATESTORE_NAME, key, token).block();
        }
        catch (Exception ex){
            logger.error("Error while storing token in cache");
            ex.printStackTrace();
        }
    }
}
