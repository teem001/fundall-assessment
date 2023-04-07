package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.configurations.JwtUtils;
import com.fundallassessment.app.dtos.requests.LoginRequest;
import com.fundallassessment.app.dtos.requests.UserRegistrationRequest;
import com.fundallassessment.app.dtos.responses.LoginResponse;
import com.fundallassessment.app.dtos.responses.RegisterationResponse;
import com.fundallassessment.app.dtos.requests.UserRegistrationResponse;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.repositories.UserRepository;
import com.fundallassessment.app.service.UserService;
import com.fundallassessment.app.utils.UserUtill;
import lombok.AllArgsConstructor;
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
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserUtill userUtill;
    private final PasswordEncoder encoder;
    private ModelMapper mapper;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<RegisterationResponse> registerUser(UserRegistrationRequest request) {

        Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail());

        if(userOptional.isPresent()){
           return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(new RegisterationResponse("User already exits", false));
        }
        User user = mapper.map(request, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(new RegisterationResponse("User registered successful", true));
    }

    @Override
    public ResponseEntity<LoginResponse> userLogin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated()){
            return  ResponseEntity.status(403).body(new LoginResponse())
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return ResponseEntity.ok(jwtUtils.generateToken(request.getEmail()));
        return null;
    }
}
