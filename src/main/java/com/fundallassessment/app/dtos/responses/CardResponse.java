package com.fundallassessment.app.dtos.responses;


import com.fundallassessment.app.entities.Card;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder


public class CardResponse extends BaseResponse {


    private String cardNumber;
    private String cardName;

    private BigDecimal cardCost;

    public CardResponse(Boolean isSuccess, String message) {
        super(isSuccess, message);
    }
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
