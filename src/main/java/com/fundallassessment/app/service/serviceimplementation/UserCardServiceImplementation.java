package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.CardRequest;
import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.entities.Card;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.UserCard;
import com.fundallassessment.app.entities.Wallet;
import com.fundallassessment.app.enums.TransactionType;
import com.fundallassessment.app.repositories.CardRepository;
import com.fundallassessment.app.repositories.UserCardRepository;
import com.fundallassessment.app.service.UserCardService;
import com.fundallassessment.app.service.WalletService;
import com.fundallassessment.app.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCardServiceImplementation implements UserCardService {
    private UserUtils utils;
    private UserCardRepository userCardRepository;
    private WalletService walletService;
    private CardRepository cardRepository;
    private PasswordEncoder encoder;
    @Override
    public ResponseEntity<CardResponse> createUserCard(CardRequest request) {
        User user = utils.getLoggedInUser();

        Optional<Card> cardOptional = cardRepository.getCardByCardNumber(request.getCardNumber());
        if(cardOptional.isEmpty()){
            return ResponseEntity.noContent().eTag("Card does not Exits").build();
        }
        Card card = cardRepository.getCardByCardNumber(request.getCardNumber()).get();
        UserCard userCard =UserCard.builder()
                .amountOnTheCard(new BigDecimal(0.00))
                .cardId(card)
                .userCardNumber((card.getCardNumber()))
                .build();

         walletService.addACardToUserWallet(user, userCard);



    }

    @Override
    public ResponseEntity<String> deleteCard(String cardNumber) {
        return null;
    }

    @Override
    public ResponseEntity<CardResponse> updateCard(String searchKey, CardRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<CardResponse> getACard(String cardNumber) {
        return null;
    }

    @Override
    public ResponseEntity<List<CardResponse>> getAllCard() {
        return null;
    }

    @Override
    public ResponseEntity<List<CardResponse>> getAllCardCreatedByUser() {
        return null;
    }
}
