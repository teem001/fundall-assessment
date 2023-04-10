package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.TransactionAnalysisRequest;
import com.fundallassessment.app.dtos.responses.TransactionAnalysisResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionAnalysisService {
    ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(TransactionAnalysisRequest period);
    ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(TransactionAnalysisRequest startPeriod, TransactionAnalysisRequest endPeriod);
}
