package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.LoginRequest;
import com.fundallassessment.app.dtos.requests.UserRegistrationRequest;
import com.fundallassessment.app.dtos.responses.RegistrationResponse;
import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import org.springframework.http.ResponseEntity;


public interface UserService {
    ResponseEntity<RegistrationResponse> registerUser(UserRegistrationRequest request);
    ResponseEntity<UserInfoResponse> userLogin(LoginRequest request);
}
