package com.example.authservice.authservice.repository;

import com.example.authservice.authservice.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByUsername(String name);

    Optional<UserModel> findByUsernameAndPassword(String username, String password);


}
