package com.fundallassessment.app.dtos.requests;

import com.fundallassessment.app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest{
    private String transactionDescription;
    private String name;
    private TransactionType type;
    private BigDecimal amount;

}
