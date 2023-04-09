package com.fundallassessment.app.dtos.responses;


import com.fundallassessment.app.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class CardResponse {


    private String cardNumber;
    private String cardName;

    private BigDecimal cardCost;
    private String message;
    private Boolean isSuccess;

    public static CardResponse mapCardToResponse(Card card){
        return CardResponse.builder()
                .cardCost(card.getCardCost())
                .cardName(card.getCardName())
                .cardNumber(card.getCardNumber())
                .build();

    }
    public static List<CardResponse> mapListOfCardsToResponse(List<Card> cards){
        return cards
                .stream()
                .map(CardResponse::mapCardToResponse)
                .collect(Collectors.toList());
    }
}
