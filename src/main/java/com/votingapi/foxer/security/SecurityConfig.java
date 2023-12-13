package com.votingapi.foxer.security;

import com.votingapi.foxer.model.User;
import com.votingapi.foxer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Optional;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    UserRepository userRepository;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/sign-up").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        return username -> {
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("USER"))
                );
            }
            throw new UsernameNotFoundException("User not found");
        };
    }
}
