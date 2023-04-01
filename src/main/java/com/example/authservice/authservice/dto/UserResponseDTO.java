package com.example.authservice.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponseDTO {

    @JsonProperty(value = "username" ,required = true)
    String username;

    @JsonProperty(value = "authcode" , required = true)
    String authcode;

    String userId;


}
