package com.example.authservice.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateTokenDTO {

    @JsonProperty(value = "authCode", required = true)
    String token;

}
