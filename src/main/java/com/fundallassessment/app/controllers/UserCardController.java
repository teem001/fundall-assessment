package com.fundallassessment.app.controllers;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.dtos.responses.UserCardResponse;
import com.fundallassessment.app.service.UserCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/assessment/user-card")
public class UserCardController {
    private final UserCardService userCardService;
    @PostMapping
    ResponseEntity<UserCardResponse> createUserCard(@RequestBody CardRequest request){
        return userCardService.createUserCard(request);
    }

    @GetMapping
    ResponseEntity<List<CardResponse>> getAllCard(){
        return userCardService.getAllCard();
    }
    @GetMapping("/user")
    ResponseEntity<List<UserCardResponse>> getAllCardByUser(){
        return userCardService.getAllCardByUser();
    }
}
