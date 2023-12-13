package com.votingapi.foxer.web.controllers;

import com.votingapi.foxer.service.UserService;
import com.votingapi.foxer.web.dto.user.UserDto;
import com.votingapi.foxer.web.dto.user.UserUpdateDto;
import com.votingapi.foxer.web.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return userService.findAll().stream()
                .map(userMapper::toPayload)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ResponseEntity::ok));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(userService.findById(id).map(userMapper::toPayload));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@userChecker.check(#id, #principal.getName())")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserUpdateDto userDto,
                                          @PathVariable Long id, Principal principal) {
        return ResponseEntity.of(userService.findById(id)
                .map(user -> userMapper.partialUpdate(userDto, user))
                .map(user -> userService.update(user, userDto.getNewPassword()))
                .map(userMapper::toPayload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, Principal principal) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
