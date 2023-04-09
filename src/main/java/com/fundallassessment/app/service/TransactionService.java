package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.TransactionRequest;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    ResponseEntity<TransactionResponse> makeATransaction(TransactionRequest request);
    ResponseEntity<List<TransactionResponse>> getAllUserTransaction();



}
