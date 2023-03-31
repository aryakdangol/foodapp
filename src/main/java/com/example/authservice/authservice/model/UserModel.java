package com.example.authservice.authservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@Getter
@Setter
@Document("users")
public class UserModel {

    @Id
    private String id;
    private String username;
    private String password;
    private String createdAt;
    private Boolean admin;

}
