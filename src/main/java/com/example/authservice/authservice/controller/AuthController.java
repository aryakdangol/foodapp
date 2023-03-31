package com.example.authservice.authservice.controller;


import com.example.authservice.authservice.dto.UserRequestDTO;
import com.example.authservice.authservice.dto.UserResponseDTO;
import com.example.authservice.authservice.dto.ValidateTokenDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<UserResponseDTO> validateToken(ValidateTokenDTO validateTokenDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
