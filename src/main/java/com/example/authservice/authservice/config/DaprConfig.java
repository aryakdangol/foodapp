package com.example.authservice.authservice.config;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class DaprConfig {

    Logger logger = LoggerFactory.getLogger(DaprConfig.class);

    @Lazy
    @Bean
    public DaprClient daprClient(){
        logger.info("CREATING DAPR BEAN.......");
        return new DaprClientBuilder().build();
    }

}
