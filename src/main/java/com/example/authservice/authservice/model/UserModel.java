package com.example.authservice.authservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("users")
public class UserModel {

    @Id
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String password;
    private LocalDate createdAt;
    private Boolean admin;

}
