package com.example.authservice.authservice;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class AuthserviceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthserviceApplication.class, args);
	}

	@Override
	public void run(String... args){


	}

}
