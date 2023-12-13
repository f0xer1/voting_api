package com.votingapi.foxer.web.controllers;

import com.votingapi.foxer.service.UserService;
import com.votingapi.foxer.web.dto.auth.Credentials;
import com.votingapi.foxer.web.dto.auth.JwtToken;
import com.votingapi.foxer.web.dto.user.UserCreationDto;
import com.votingapi.foxer.web.dto.user.UserDto;
import com.votingapi.foxer.web.mapper.JwtTokenMapper;
import com.votingapi.foxer.web.mapper.UserMapper;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtTokenMapper jwtTokenMapper;
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid UserCreationDto userDto)  {
        var newUser = userService.signUp(userMapper.toEntity(userDto));
        return new ResponseEntity<>(userMapper.toPayload(newUser), HttpStatus.CREATED);
    }

    public ResponseEntity<JwtToken> signIn(@RequestBody @Valid Credentials credentials) {
        return ResponseEntity.of(userService
                .signIn(credentials.getUsername(), credentials.getPassword())
                .map(jwtTokenMapper::toPayload));
    }
}
