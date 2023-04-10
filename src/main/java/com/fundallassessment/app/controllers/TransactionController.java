package com.fundallassessment.app.controllers;

import com.fundallassessment.app.dtos.requests.TransactionAnalysisRequest;
import com.fundallassessment.app.dtos.requests.TransactionRequest;
import com.fundallassessment.app.dtos.responses.TransactionAnalysisResponse;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import com.fundallassessment.app.service.TransactionAnalysisService;
import com.fundallassessment.app.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/assessment/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;
    private TransactionAnalysisService analysisService;
    @PostMapping("/create")
    ResponseEntity<TransactionResponse> makeATransaction(@RequestBody TransactionRequest request){
        return transactionService.makeATransaction(request);

    }
    @GetMapping
    ResponseEntity<List<TransactionResponse>> getAllUserTransaction(){
        return transactionService.getAllUserTransaction();
    }
    @GetMapping("analysis")
    ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(@RequestBody TransactionAnalysisRequest period){
        return analysisService.getAllTransactionForPeriod(period);
    }
    @GetMapping("custom-analysis")
    ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(@RequestBody TransactionAnalysisRequest startPeriod, @RequestBody TransactionAnalysisRequest endPeriod){
        return analysisService.getAllTransactionForPeriod(startPeriod,endPeriod);
    }

}
