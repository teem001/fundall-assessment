package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.TransactionRequest;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.Transaction;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.Wallet;
import com.fundallassessment.app.enums.TransactionStatus;
import com.fundallassessment.app.repositories.TransactionRepository;
import com.fundallassessment.app.repositories.WalletRepository;
import com.fundallassessment.app.service.TransactionService;
import com.fundallassessment.app.service.WalletService;
import com.fundallassessment.app.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImplementation implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserUtils utils;
    private final WalletService walletService;

    @Override
    @Transactional
    public ResponseEntity<TransactionResponse> makeATransaction(TransactionRequest request) {
        log.info("transaction processing {}", request);
        User user = utils.getLoggedInUser();
        Transaction transaction = Transaction.builder()
                .transactionStatus(TransactionStatus.PENDING)
                .referenceNumber(Timestamp.valueOf(LocalDateTime.now()).toString())
                .transactionDescription(request.getTransactionDescription())
                .type(request.getType())
                .amount(request.getAmount())
                .name(request.getName())
                .build();
        transactionRepository.saveAndFlush(transaction);


      UserInfoResponse userInfoResponse = walletService.setCashFlow(user, request.getAmount(),request.getType());
      if(userInfoResponse.getIsSuccess()==null){
          System.out.println("Inside null");
          transaction.setTransactionStatus(TransactionStatus.FAIL);
          transactionRepository.save(transaction);
          return ResponseEntity.badRequest().body(TransactionResponse.mapFromTransaction(transaction));
      }



        else
            if(userInfoResponse.getIsSuccess()){
                System.out.println("inside success");
          log.info("transaction was successful {}", userInfoResponse);

          transaction.setTransactionStatus(TransactionStatus.SUCCESS);

          transactionRepository.save(transaction);

          return ResponseEntity.ok(TransactionResponse.mapFromTransaction(transaction));

        }
      else{
            log.error("transaction was unsuccessful {}" , userInfoResponse);

            transaction.setTransactionStatus(TransactionStatus.FAIL);

            transactionRepository.save(transaction);

            return ResponseEntity.badRequest().body(TransactionResponse.mapFromTransaction(transaction));



        }





    }
}
