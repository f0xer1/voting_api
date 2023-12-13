package com.votingapi.foxer.controllers;

import com.votingapi.foxer.model.User;
import com.votingapi.foxer.service.UserService;
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
