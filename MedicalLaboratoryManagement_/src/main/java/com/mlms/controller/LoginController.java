package com.mlms.controller;

import com.mlms.config.JwtGeneratorValidator;
import com.mlms.dtos.UserDTO;
import com.mlms.repo.UserRepo;
import com.mlms.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtGeneratorValidator jwtGenVal;

    @Autowired
    BCryptPasswordEncoder bcCryptPasswordEncoder;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public ResponseEntity<String> generateJwtToken(@RequestBody UserDTO userDto) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtGenVal.generateToken(authentication);

            return ResponseEntity.ok(jwtToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
