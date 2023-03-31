package com.example.authservice.authservice.repository;

import com.example.authservice.authservice.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

}
