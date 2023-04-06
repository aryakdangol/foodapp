package com.example.authservice.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValidateTokenDTO {

    @JsonProperty(value = "id", required = true)
    String userid;
    @JsonProperty(value = "authCode", required = false)
    String token;

}
