package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> getUser(UUID uuid, Jwt jwt) {
        return userRepository.findById(uuid)
                .switchIfEmpty(Mono.defer(() -> {
                    log.info("New Id: " + uuid);
                    return userRepository.save(new User(uuid, 5));
                }))
                .cast(User.class);

    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
}
