package com.votingapi.foxer.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.votingapi.foxer.model.User;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<DecodedJWT> signIn(String username, String password);

    User signUp(User user) ;

    User update(User user, String newPassword);

    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);
    void deleteById(Long userId);
}
