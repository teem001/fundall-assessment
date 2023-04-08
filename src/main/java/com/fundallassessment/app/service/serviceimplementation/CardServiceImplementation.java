package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.entities.Card;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.Wallet;
import com.fundallassessment.app.enums.Role;
import com.fundallassessment.app.repositories.CardRepository;
import com.fundallassessment.app.service.CardService;
import com.fundallassessment.app.service.WalletService;
import com.fundallassessment.app.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class CardServiceImplementation implements CardService {

    private final CardRepository cardRepository;
    private final UserUtils userUtils;
    private final WalletService walletService;
    @Override
    public ResponseEntity<CardResponse> createCard(CardRequest request) {

        User userInSession = userUtils.getLoggedInUser();
        log.info("create new card for {}", userInSession.toString());
        if(userInSession.getRole()== Role.USER){
            log.error("Unauthorised user");
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(
                    new CardResponse(false, "AUTHORIZED USER")
            );
        }
        log.info("processing the card");

        Card card = Card.builder()
                .cardCost(request.getCardCost())
                .cardNumber(request.getCardNumber())
                .cardName(request.getCardName())
                .createdBy(userInSession)
                .build();
        cardRepository.save(card);
        CardResponse response = new CardResponse(true, "Successful");

        BeanUtils.copyProperties(request, response);
        log.info("Card created {}" ,card);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> deleteCard(String cardNumber) {
        Optional<Card> card = cardRepository.getCardByCardNumberOOrCardName(cardNumber);
        if(card.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        cardRepository.delete(card.get());
        return ResponseEntity.ok("Card successfully deleted");

    }

    @Override
    @Transactional
    public ResponseEntity<CardResponse> updateCard(String cardNumber, CardRequest request) {
        Optional<Card> card = cardRepository.getCardByCardNumberOOrCardName(cardNumber);
        return card.map(
                value->
                        cardRepository
                                .save(Card.builder()
                                        .cardName(request.getCardName())
                                        .cardNumber(request.getCardNumber())
                                        .cardCost(request.getCardCost())
                                        .build()))
                .map(value->
                        ResponseEntity
                                .ok(CardResponse
                                        .mapCardToResponse(value)))
                .orElseGet(()->
                        ResponseEntity.notFound()
                                .build()
                );


    }

    @Override
    public ResponseEntity<CardResponse> getACard(String searchKey) {
        Optional<Card> card = cardRepository.getCardByCardNumberOOrCardName(searchKey);
        return card.map(value -> ResponseEntity
                .ok(CardResponse
                        .mapCardToResponse(
                                value)
                )).orElseGet(() -> ResponseEntity
                .noContent().build());
    }

    @Override
    public ResponseEntity<List<CardResponse>> getAllCard() {
        Optional<List<Card>> cardsAvailable = cardRepository.getAllCard();
        return cardsAvailable.map(
                cards ->
                        ResponseEntity.ok(
                                CardResponse.mapListOfCardsToResponse(cards)))
                .orElseGet(() ->
                        ResponseEntity.noContent().build()
                );


    }

    @Override
    public ResponseEntity<List<CardResponse>> getAllCardCreatedByUser() {
        User user = userUtils.getLoggedInUser();
        Optional<List<Card>> cardsAvailable = cardRepository.getAllByCreatedBy(user);
        return cardsAvailable.map(
                        cards ->
                                ResponseEntity.ok(
                                        CardResponse.mapListOfCardsToResponse(cards)))
                .orElseGet(() ->
                        ResponseEntity.noContent().build()
                );
    }
}
