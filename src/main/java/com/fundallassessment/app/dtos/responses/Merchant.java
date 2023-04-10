package com.fundallassessment.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Merchant {
    private String name;
    private Integer numberOfTransaction;
    private BigDecimal totalAmount;
}
