package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.requests.TransactionRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import com.fundallassessment.app.dtos.responses.UserCardResponse;
import com.fundallassessment.app.entities.*;
import com.fundallassessment.app.enums.TransactionType;
import com.fundallassessment.app.repositories.CardRepository;
import com.fundallassessment.app.repositories.UserCardRepository;
import com.fundallassessment.app.repositories.WalletRepository;
import com.fundallassessment.app.service.CardService;
import com.fundallassessment.app.service.TransactionService;
import com.fundallassessment.app.service.UserCardService;
import com.fundallassessment.app.service.WalletService;
import com.fundallassessment.app.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserCardServiceImplementation implements UserCardService {
    private final UserUtils utils;
    private final UserCardRepository userCardRepository;
    private final WalletService walletService;
    private final CardRepository cardRepository;
    private final WalletRepository walletRepository;
    private final CardService cardService;
    private final TransactionService transactionService;
    @Override
    @Transactional
    public ResponseEntity<UserCardResponse> createUserCard(@NonNull CardRequest request) {
        User user = utils.getLoggedInUser();


        Optional<Card> cardOptional = cardRepository.getCardByCardNumber(request.getCardNumber());
        if(cardOptional.isPresent()){
            return ResponseEntity
                    .badRequest().body(
                            UserCardResponse.builder()
                                    .message("card already exits")
                                    .isSuccess(false)
                                    .build()
                    );
        }
        Card card = cardRepository.getCardByCardNumber(request.getCardNumber()).get();
        UserCard userCard =UserCard.builder()
                .amountOnTheCard(new BigDecimal("0.00"))
                .card(card)
                .userCardNumber((card.getCardNumber()))
                .build();

        TransactionRequest transactionRequest = TransactionRequest.builder()
                .transactionDescription(request.getCardName() + " " + request.getCardNumber())
                .type(TransactionType.DEBIT)
                .amount(request.getCardCost())
                .name("Card payment")
                .build();
       TransactionResponse transactionResponse = transactionService.makeATransaction(transactionRequest).getBody();
        Optional<Wallet> wallet = walletRepository.findWalletByUser(user);

        boolean isSuccess = wallet.get().getTransactions().stream().anyMatch(p-> {
            assert transactionResponse != null;
            return p.getId().equals(transactionResponse.getTransactionId());
        });





       return wallet.filter(
               p-> isSuccess)
               .map(
                       p -> {userCardRepository.save(userCard);
                           p.getUserCards().add(userCard);

                          return ResponseEntity
                               .ok(
                                       UserCardResponse
                                               .builder()
                                               .userInfoResponse(walletService.returnUserWalletInfo(user))
                                               .cardName(request.getCardName())
                                               .amount(request.getCardCost())
                                               .isSuccess(true)
                                               .message("card created")
                                               .build());})
               .orElseGet(
                       ()->
                               ResponseEntity
                                       .badRequest()
                                       .build());



    }



    @Override
    public ResponseEntity<List<CardResponse>> getAllCard() {
        return cardService.getAllCard();
    }

    @Override
    public ResponseEntity<List<UserCardResponse>> getAllCardByUser() {
        User user =utils.getLoggedInUser();
       Optional <Wallet> wallet = walletRepository.findWalletByUser(user);
      if (wallet.isEmpty()){
           return ResponseEntity.noContent().build();
        }

       Set<UserCard> userCards = wallet.get().getUserCards();
       List<UserCardResponse> cardResponseList = new ArrayList<>();
       for(UserCard card: userCards){
           UserCardResponse cardResponse = UserCardResponse.builder()
                   .userInfoResponse(walletService.returnUserWalletInfo(user))
                   .cardName(card.getCard().getCardName())
                   .isSuccess(true)
                   .message("available")
                   .amount(card.getAmountOnTheCard())
                   .build();
           cardResponseList.add(cardResponse);
       }
       return ResponseEntity.ok(cardResponseList);



    }
}
