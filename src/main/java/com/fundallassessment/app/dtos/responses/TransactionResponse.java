package com.fundallassessment.app.dtos.responses;

import com.fundallassessment.app.enums.TransactionStatus;

import java.math.BigDecimal;

public class TransactionResponse {

    private String transactionReference;
    private String transactionDescription;
    private TransactionStatus transactionStatus;
    private BigDecimal amount;
}
