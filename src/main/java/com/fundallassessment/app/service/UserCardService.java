package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.dtos.responses.UserCardResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserCardService {
    ResponseEntity<UserCardResponse> createUserCard(CardRequest request);

    ResponseEntity<List<CardResponse>> getAllCard();
    ResponseEntity<List<CardResponse>> getAllCardByUser();
}
