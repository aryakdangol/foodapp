package com.example.authservice.authservice.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authservice.authservice.dto.UserRequestDTO;
import com.example.authservice.authservice.dto.UserResponseDTO;
import com.example.authservice.authservice.dto.ValidateTokenDTO;
import com.example.authservice.authservice.exception.UserExistException;
import com.example.authservice.authservice.model.UserModel;
import com.example.authservice.authservice.repository.TokenRepository;
import com.example.authservice.authservice.repository.UserRepository;
import com.example.authservice.authservice.service.UserService;
import com.example.authservice.authservice.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    TokenRepository tokenRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponseDTO register(UserRequestDTO userResponseDTO) {
        String username = userResponseDTO.getUsername();

        if(checkUserExist(username)){
            throw new UserExistException(username);
        }
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(userResponseDTO.getPassword()));
        user.setCreatedAt(LocalDate.now());
        user.setAdmin(false);

       UserModel savedUser = userRepository.save(user);
       String token = generateJwt(savedUser.getUsername(), savedUser.getId());
       tokenRepository.saveToken(savedUser.getId(), token);

       UserResponseDTO response = new UserResponseDTO();
       response.setUsername(savedUser.getUsername());
       response.setAuthcode(token);
       response.setUserId(savedUser.getId());


       return response;
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public ValidateTokenDTO validate(ValidateTokenDTO validateTokenDTO) {
        return null;
    }

    private Boolean checkUserExist(String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    private String generateJwt(String username, String userid){

        return JWT.create()
                .withIssuer("auth-service")
                .withClaim("userId", userid)
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + AppConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(AppConstants.SECRET_KEY));
    }

}
