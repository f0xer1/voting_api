package com.workshop.bouali.controllers;

import com.workshop.bouali.pojo.User;
import com.workshop.bouali.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController  {
    UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody User user){
        return userService.createUser(user);
    }
}
