package com.example.userservice;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UserRepository userRepository) {
		User user1 = new User(UUID.randomUUID(), "Barış Çetintürk", "baris@gmail.com", "123", "0534", 0);

		return args -> {
			userRepository
					.deleteAll()
					.then(userRepository.save(user1))
					.then(userRepository.findById(user1.getId()))
					.subscribe(user -> log.info("saved " + user1.toString()));
		};
	}
}
