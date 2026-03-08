package com.alurachallenges.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import com.alurachallenges.forohub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.alurachallenges.forohub.domain.usuario.User;

@SpringBootApplication
public class ForohubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

	@Bean
	public CommandLineRunner createTestUser(@Autowired UserRepository userRepository, @Autowired PasswordEncoder encoder) {
		return args -> {
			var create = System.getenv("CREATE_TEST_USER");
				if ("true".equalsIgnoreCase(create)) {
					var existing = userRepository.findByEmail("test@example.com");
					if (existing == null) {
						var user = new User();
						user.setName("Test User");
						user.setEmail("test@example.com");
						user.setPassword(encoder.encode("secret"));
						userRepository.save(user);
						System.out.println("Test user created: test@example.com");
					}
				}
		};
	}

}

