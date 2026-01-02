package com.security.demo.init;

import com.security.demo.role.Role;
import com.security.demo.role.RoleRepository;
import com.security.demo.user.User;
import com.security.demo.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(RoleRepository roleRepo,
                               UserRepository userRepo,
                               PasswordEncoder passwordEncoder) {

        return args -> {

            // ✅ Create roles if not exist
            Role adminRole = roleRepo.findByRoleName("ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoleName("ADMIN");
                        return roleRepo.save(role);
                    });

            Role userRole = roleRepo.findByRoleName("USER")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoleName("USER");
                        return roleRepo.save(role);
                    });

            // ✅ Create ADMIN user
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));
                userRepo.save(admin);
            }

            // ✅ Create USER
            if (userRepo.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
            }

            System.out.println("✅ Default users & roles initialized");
        };
    }
}

