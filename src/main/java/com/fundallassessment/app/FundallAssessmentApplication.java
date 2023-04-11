package com.fundallassessment.app;

import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.enums.Role;
import com.fundallassessment.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class FundallAssessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundallAssessmentApplication.class, args);
    }

    @Component
    @AllArgsConstructor
    public static class IntialSetup {
        private final PasswordEncoder encoder;
        private final UserRepository userRepository;


        @EventListener
        @Transactional
        public void onApplicationEvent(ApplicationReadyEvent event) {
            System.out.println("From Application ready event....");


            User superAdminUser = new User();
            superAdminUser.setFirstName("Super");
            superAdminUser.setLastName("Super");
            superAdminUser.setEmail("super@admin.com");
            superAdminUser.setPassword(encoder.encode("123456789"));
            superAdminUser.setPhoneNumber("08000000000");
            superAdminUser.setRole(Role.SUPER_ADMIN);

            User storedSuperUser = userRepository.findUserByEmail("super@admin.com").orElse(null);
            if(storedSuperUser == null) {
                userRepository.save(superAdminUser);
            }
        }

    }
}
