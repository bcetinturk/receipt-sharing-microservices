package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable("id") UUID id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public Mono<User> saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}
