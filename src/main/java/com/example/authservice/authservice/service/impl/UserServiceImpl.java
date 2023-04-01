package com.example.authservice.authservice.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.authservice.authservice.dto.UserRequestDTO;
import com.example.authservice.authservice.dto.UserResponseDTO;
import com.example.authservice.authservice.dto.ValidateTokenDTO;
import com.example.authservice.authservice.exception.AuthServiceException;
import com.example.authservice.authservice.model.UserModel;
import com.example.authservice.authservice.repository.TokenRepository;
import com.example.authservice.authservice.repository.UserRepository;
import com.example.authservice.authservice.service.UserService;
import com.example.authservice.authservice.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    TokenRepository tokenRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponseDTO register(UserRequestDTO userResponseDTO) throws AuthServiceException {
        String username = userResponseDTO.getUsername();

        if(checkUserExist(username) != null){
            throw new AuthServiceException(
                    HttpStatus.BAD_REQUEST,
                    "Username: " + username + " already exists",
                    "ERR_ALREADY_EXISTS"
            );
        }
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(userResponseDTO.getPassword()));
        user.setCreatedAt(LocalDate.now());
        user.setAdmin(false);

       UserModel savedUser = userRepository.save(user);
       String token = generateJwt(savedUser.getUsername(), savedUser.getId());
       tokenRepository.saveToken(savedUser.getId(), token);

       return generateUserResponseDTO(savedUser, token);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) throws AuthServiceException {
        String username = userRequestDTO.getUsername();

        UserModel user = checkUserExist(username);

        if(user == null){
            throw new AuthServiceException(HttpStatus.NOT_FOUND,
                    "Can't find user with username: " + username,
                    "ERR_NOT_FOUND");
        }

        if(!bCryptPasswordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())){
            throw new AuthServiceException(HttpStatus.FORBIDDEN,
                    "Password is invalid!",
                    "ERR_AUTHENTICATION_FAIL"
                    );
        }

        String token = generateJwt(user.getId(), user.getUsername());

        tokenRepository.deleteToken(user.getId());
        tokenRepository.saveToken(user.getId(), token);
        return generateUserResponseDTO(user, token);
    }

    @Override
    public ValidateTokenDTO validate(String bearerToken, ValidateTokenDTO validateTokenDTO) throws AuthServiceException {
        if(bearerToken.isEmpty() || bearerToken.isBlank() || !bearerToken.startsWith("Bearer ")){
            throw new AuthServiceException(
                    HttpStatus.UNAUTHORIZED,
                    "Please provide a token",
                    "ERR_TOKEN_EMPTY"
            );
        }

        String reqToken = bearerToken.replace("Bearer ", "");

        Optional<String> cachedToken = tokenRepository.getToken(validateTokenDTO.getUserid());

        if(cachedToken.isEmpty()){
            try{
                String decoded = JWT.require(Algorithm.HMAC512(AppConstants.SECRET_KEY))
                        .build()
                        .verify(reqToken)
                        .getSubject();
                System.out.println("DECODED STRING: " + decoded);
                validateTokenDTO.setToken(reqToken);
                return validateTokenDTO;
            }
            catch(RuntimeException ex){
                throw new AuthServiceException(
                        HttpStatus.UNAUTHORIZED,
                        ex.getMessage(),
                        "ERR_INVALID_TOKEN"
                );
            }

        }

        if(!reqToken.matches(cachedToken.get())){
            throw new AuthServiceException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid Token!",
                    "ERR_INVALID_TOKEN"
            );
        }

        validateTokenDTO.setToken(cachedToken.get());
        return validateTokenDTO;
    }

    private UserModel checkUserExist(String username){
        Optional<UserModel> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    private String generateJwt(String username, String userid){

        return JWT.create()
                .withIssuer("auth-service")
                .withClaim("userId", userid)
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + AppConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(AppConstants.SECRET_KEY));
    }

    private UserResponseDTO generateUserResponseDTO(UserModel user, String token){
        UserResponseDTO response = new UserResponseDTO();
        response.setUsername(user.getUsername());
        response.setAuthcode(token);
        response.setUserId(user.getId());
        return response;
    }

}
