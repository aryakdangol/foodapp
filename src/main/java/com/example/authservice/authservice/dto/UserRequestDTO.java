package com.example.authservice.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequestDTO {
    @JsonProperty(value = "username", required = true)
    @NotBlank(message = "Please provide a username")
    String username;
    @JsonProperty(value = "password", required = true)
    @NotBlank(message = "Password cannot be empty")
    String password;

}
