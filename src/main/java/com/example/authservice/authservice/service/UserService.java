package com.example.authservice.authservice.service;

import com.example.authservice.authservice.dto.UserRequestDTO;
import com.example.authservice.authservice.dto.UserResponseDTO;
import com.example.authservice.authservice.dto.ValidateTokenDTO;
import com.example.authservice.authservice.exception.AuthServiceException;

public interface UserService {

    public UserResponseDTO register(UserRequestDTO userResponseDTO) throws AuthServiceException;
    public UserResponseDTO login(UserRequestDTO userRequestDTO) throws AuthServiceException;
    public ValidateTokenDTO validate(String bearerToken, ValidateTokenDTO validateTokenDTO) throws AuthServiceException;

}
