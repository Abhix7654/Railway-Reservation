package com.railway.user.service;


import com.railway.user.service.entity.User;
import com.railway.user.service.enums.Role;
import com.railway.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initAdmin(UserRepository repo, PasswordEncoder encoder) {
		return args -> {
			if (repo.findByEmail("admin@railway.com").isEmpty()) {
				repo.save(User.builder()
						.email("admin@railway.com")
						.name("Master Admin")
						.password(encoder.encode("admin123"))
						.role(Role.ADMIN)
						.build());
			}
		};
	}
}
