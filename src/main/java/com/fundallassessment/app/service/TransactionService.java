package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.TransactionRequest;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<TransactionResponse> makeATransaction(TransactionRequest request);

}
