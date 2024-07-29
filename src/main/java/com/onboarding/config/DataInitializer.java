package com.onboarding.config;

import com.onboarding.entity.User;
import com.onboarding.entity.enums.Role;
import com.onboarding.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
//    @Bean
//    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            userRepository.save(new User("sam", passwordEncoder.encode("2300"), Role.EMPLOYEE));
//            userRepository.save(new User("somanath", passwordEncoder.encode("2300"), Role.HR));
//        };
//    }
}
