package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.dtos.responses.WalletResponse;
import com.fundallassessment.app.entities.Card;
import com.fundallassessment.app.entities.Transaction;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.Wallet;
import com.fundallassessment.app.repositories.WalletRepository;
import com.fundallassessment.app.service.WalletService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class WalletServiceImplementation implements WalletService {
    private final WalletRepository walletRepository;
    private final PasswordEncoder encoder;
    private final Dotenv dotenv;
    private final ModelMapper mapper
    @Override
     public void createWallet(User user) {
        Wallet wallet = Wallet.builder()
                .accountBalance( BigDecimal.valueOf(0.00))
                .accountNumber(user.getPhoneNumber().trim().substring(1))
                .income(BigDecimal.valueOf(0.00))
                .spent(BigDecimal.valueOf(0.00))
                .user(user)
                .pin(encoder.encode(dotenv.get("INITIAL_WALLET_PIN")))
                .build();

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserInfoResponse returnUserWalletInfo(User user) {

        Wallet wallet = walletRepository.findWalletByUser(user).get();


        UserInfoResponse userInfoResponse = new UserInfoResponse();
        List<CardResponse> cardResponseList = new ArrayList<>();
        for(Card card : wallet.getUserCards()){
            CardResponse cardResponse = mapper.map(card, CardResponse.class);
            cardResponseList.add(cardResponse);
        }

        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for(Transaction transaction : wallet.getTransactions()){
            TransactionResponse transactionResponse = mapper.map(transaction, TransactionResponse.class);
            transactionResponses.add(transactionResponse);
        }

        userInfoResponse.setWalletResponseDetail(
                wallet.getAccountNumber(),
                wallet.getAccountBalance(),
                wallet.getIncome(),
                wallet.getSpent(),
                cardResponseList,
                transactionResponses);
        return userInfoResponse;
    }
}
