package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CardService {
    ResponseEntity<CardResponse> createCard(CardRequest request);
    ResponseEntity<String > deleteCard(String cardNumber);
    ResponseEntity<CardResponse> updateCard(String searchKey,CardRequest request );
    ResponseEntity<CardResponse> getACard(String cardNumber);
    ResponseEntity<List<CardResponse>> getAllCard();
    ResponseEntity<List<CardResponse>> getAllCardCreatedByUser();
}
