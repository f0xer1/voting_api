package com.votingapi.foxer.security.checker;

import com.votingapi.foxer.exeption.UserNotFoundException;
import com.votingapi.foxer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {
    private final UserRepository userRepository;

    public boolean check(Long id, String username) {
        if (id == null || username == null) {
            return false;
        }
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id %s not found".formatted(id))
        );
        return user.getUsername().equals(username);
    }
}
