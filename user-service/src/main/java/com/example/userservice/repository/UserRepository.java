package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface UserRepository extends ReactiveMongoRepository<User, UUID> {
}
