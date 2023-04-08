package com.fundallassessment.app.controllers;

import com.fundallassessment.app.dtos.requests.LoginRequest;
import com.fundallassessment.app.dtos.requests.UserRegistrationRequest;
import com.fundallassessment.app.dtos.responses.RegistrationResponse;
import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/assessment/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody UserRegistrationRequest request){
        return userService.registerUser(request);
    }
    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> userLogin(@RequestBody LoginRequest request){
        return userService.userLogin(request);
    }

}
