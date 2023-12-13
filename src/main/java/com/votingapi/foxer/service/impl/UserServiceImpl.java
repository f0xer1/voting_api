package com.votingapi.foxer.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.votingapi.foxer.exeption.InvalidPasswordException;
import com.votingapi.foxer.exeption.RoleNotFoundException;
import com.votingapi.foxer.exeption.UserAlreadyExistsException;
import com.votingapi.foxer.exeption.UserNotFoundException;
import com.votingapi.foxer.model.User;
import com.votingapi.foxer.repository.RoleRepository;
import com.votingapi.foxer.repository.UserRepository;
import com.votingapi.foxer.security.JwtTokenProvider;
import com.votingapi.foxer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public Optional<DecodedJWT> signIn(String username, String password) {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username %s not found".formatted(username))
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return jwtTokenProvider.toDecodedJWT(
                jwtTokenProvider.generateToken(user.getId(), username, List.copyOf(user.getRoles())));
    }

    @Override
    @Transactional
    public User signUp(User user)  {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Username %s is already in use".formatted(user.getUsername()));
        }
        user.addRole(roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new RoleNotFoundException("Role 'USER' not found. Failed to assign to new user")
        ));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User update(User user, String newPassword) {
        if (isUsernameInUse(user)) {
            throw new UserAlreadyExistsException(
                    "Username %s is already in use".formatted(user.getUsername()));
        }
        if (newPassword != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private boolean isUsernameInUse(User user) {
        return userRepository.findByUsername(user.getUsername())
                .filter(found -> !found.getId().equals(user.getId())).isPresent();
    }
}
