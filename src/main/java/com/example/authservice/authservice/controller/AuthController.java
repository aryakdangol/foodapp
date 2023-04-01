package com.example.authservice.authservice.controller;


import com.example.authservice.authservice.dto.UserRequestDTO;
import com.example.authservice.authservice.dto.UserResponseDTO;
import com.example.authservice.authservice.dto.ValidateTokenDTO;
import com.example.authservice.authservice.exception.AuthServiceException;
import com.example.authservice.authservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@AllArgsConstructor
public class AuthController {

    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws AuthServiceException {
        return new ResponseEntity<>(userService.register(userRequestDTO), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@Valid @RequestBody UserRequestDTO userRequestDTO)throws AuthServiceException{
        return new ResponseEntity<>(userService.login(userRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validateToken(@RequestHeader("Authorization") String bearerToken, @RequestBody ValidateTokenDTO validateTokenDTO) throws AuthServiceException {
        return new ResponseEntity<>(userService.validate(bearerToken, validateTokenDTO), HttpStatus.OK);
    }

}
