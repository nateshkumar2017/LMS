package com.mlms.controller;

import com.mlms.config.JwtGeneratorValidator;
import com.mlms.dtos.UserDTO;
import com.mlms.entities.User;
import com.mlms.repo.UserRepo;
import com.mlms.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

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

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDto) {
        User users =  userService.save(userDto);
        if (users.equals(null))
            return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, userDto);
        else
            return generateRespose("User saved successfully : " + users.getId(), HttpStatus.OK, users);
    }

    @GetMapping("/genToken")
    public String generateJwtToken(@RequestBody UserDTO userDto) throws Exception {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //System.out.println(jwtGenVal.generateToken(authentication));
        return jwtGenVal.generateToken(authentication);
    }

    public ResponseEntity<Object> generateRespose(String message, HttpStatus st, Object responseobj) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("meaasge", message);
        map.put("Status", st.value());
        map.put("data", responseobj);

        return new ResponseEntity<Object>(map, st);
    }


}
