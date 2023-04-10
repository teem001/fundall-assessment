package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.configurations.JwtUtils;
import com.fundallassessment.app.dtos.requests.LoginRequest;
import com.fundallassessment.app.dtos.requests.UserRegistrationRequest;
import com.fundallassessment.app.dtos.responses.RegistrationResponse;
import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.enums.Role;
import com.fundallassessment.app.repositories.UserRepository;
import com.fundallassessment.app.service.UserService;
import com.fundallassessment.app.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private ModelMapper mapper;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private WalletService walletService;

    @Override
    public ResponseEntity<RegistrationResponse> registerUser(UserRegistrationRequest request)
    {

        Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail());

        if(userOptional.isPresent()){
           return ResponseEntity.status(HttpStatusCode.valueOf(403))
                   .body(new RegistrationResponse("User already exits", false));
        }

        User user = mapper.map(request, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        walletService.createWallet(user);

        return ResponseEntity.status(HttpStatusCode.valueOf(200))
                .body(new RegistrationResponse("User registered successful", true));
    }

    @Override
    public ResponseEntity<UserInfoResponse> userLogin(LoginRequest request)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request
                                .getEmail(),
                        request
                                .getPassword())
        );
        if(!authentication.isAuthenticated()){
            return  ResponseEntity
                    .status(403)
                    .body(
                            new UserInfoResponse("invalid password", false)
                    );
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        String token = jwtUtils.generateToken(request.getEmail());
        User user = userRepository
                .findUserByEmail(
                        authentication
                                .getName()
                )
                .get();
        UserInfoResponse loginResponse = walletService.returnUserWalletInfo(user);

        loginResponse.setMessage("login successful");
        loginResponse.setIsSuccess(true);
        loginResponse.setImgPath(user.getImageUrl());
        loginResponse.setToken(token);

        log.info("User in session is {}", principal.toString());


        return ResponseEntity.ok(loginResponse);
    }
}
