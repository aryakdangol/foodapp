package com.example.authservice.authservice.repository.impl;

import com.example.authservice.authservice.repository.TokenRepository;
import com.example.authservice.authservice.util.AppConstants;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenRepositoryImpl implements TokenRepository {

    Logger logger = LoggerFactory.getLogger(TokenRepositoryImpl.class);
    @Lazy
    @Autowired
    DaprClient client;

    @Override
    public Optional<String> getToken(String key) {
       try{
           State<String> token = client.getState(AppConstants.STATESTORE_NAME, key, String.class).block();
            return Optional.ofNullable(token.getValue());
       }
       catch (Exception e){
           logger.error("Error while retrieving token in cache");
           e.printStackTrace();

        }
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

    @Override
    public void deleteToken(String key) {
        try {
            client.deleteState(AppConstants.STATESTORE_NAME, key).block();
        } catch (Exception ex) {
            logger.error("Error while deleting token in cache");
            ex.printStackTrace();
        }
    }
}
