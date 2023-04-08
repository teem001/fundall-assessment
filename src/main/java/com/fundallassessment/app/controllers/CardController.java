package com.fundallassessment.app.controllers;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/assessment/card")
@AllArgsConstructor
public class CardController {
    private CardService cardService;

    @PostMapping("/create")
    ResponseEntity<CardResponse> createCard(@RequestBody CardRequest request){
        return cardService.createCard(request);
    }
    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllCard(){
        return cardService.getAllCard();
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String > deleteCard(@PathVariable("id") String cardNumber){
        return cardService.deleteCard(cardNumber);
    }
    @PutMapping("/{searchKey}")
    ResponseEntity<CardResponse> updateCard(@PathVariable("searchKey") String searchKey, @RequestBody CardRequest request ){
        return cardService.updateCard(searchKey,request);
    }
    @GetMapping("/{cardNumber}")
    ResponseEntity<CardResponse> getACard(@PathVariable("cardNumber") String cardNumber){
        return cardService.getACard(cardNumber);
    }
    @GetMapping("/user")
    ResponseEntity<List<CardResponse>> getAllCardCreatedByUser(){
        return cardService.getAllCardCreatedByUser();
    }



}
