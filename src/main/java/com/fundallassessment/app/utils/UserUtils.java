package com.fundallassessment.app.utils;

import com.fundallassessment.app.entities.User;

import com.fundallassessment.app.execptions.ResourceNotFoundException;
import com.fundallassessment.app.execptions.UserNotFoundException;
import com.fundallassessment.app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;


@Component
@AllArgsConstructor
@Slf4j
public class UserUtils {
    private final UserRepository userRepository;
    public User getLoggedInUser() throws ResourceNotFoundException {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(principal.toString());

        return userRepository.findUserByEmail(principal.toString())
                .orElseThrow(() -> new UserNotFoundException("Error getting logged in user"));
    }

}
