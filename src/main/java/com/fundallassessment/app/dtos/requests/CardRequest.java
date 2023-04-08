package com.fundallassessment.app.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    private String cardNumber;
    private String cardName;
    private BigDecimal cardCost;
}
