package com.example.authservice.authservice.service;

import com.example.authservice.authservice.dto.UserRequestDTO;
import com.example.authservice.authservice.dto.UserResponseDTO;
import com.example.authservice.authservice.dto.ValidateTokenDTO;

public interface UserService {

    public UserResponseDTO register(UserRequestDTO userResponseDTO);
    public UserResponseDTO login(UserRequestDTO userRequestDTO);
    public ValidateTokenDTO validate(ValidateTokenDTO validateTokenDTO);

}
