package com.fundallassessment.app.dtos.responses;

import com.fundallassessment.app.entities.Transaction;
import com.fundallassessment.app.enums.TransactionStatus;
import com.fundallassessment.app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private Long transactionId;
    private String name;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime dateOfTransaction;
    private TransactionStatus transactionStatus;
    private String description;
    private Boolean isSuccess;


    public static TransactionResponse mapFromTransaction (Transaction transaction){
        return TransactionResponse.builder()
                .transactionId(transaction.getId())
                .name(transaction.getName())
                .amount(transaction.getAmount())
                .transactionType(transaction.getType())
                .dateOfTransaction(transaction.getCreatedAt())
                .transactionStatus(transaction.getTransactionStatus())
                .description(transaction.getTransactionDescription())
                .build();
    }

    public static List<TransactionResponse> mapFromTransaction (List<Transaction> transactions){
        return  transactions
                .stream()
                .map(TransactionResponse::mapFromTransaction)
                .collect(Collectors.toList());
    }
}
