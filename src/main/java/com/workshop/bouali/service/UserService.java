package com.workshop.bouali.service;

import com.workshop.bouali.pojo.User;
import com.workshop.bouali.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> createUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>("failed",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }
}
