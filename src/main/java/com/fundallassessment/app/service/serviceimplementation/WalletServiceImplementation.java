package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.responses.CardResponse;
import com.fundallassessment.app.dtos.responses.TransactionResponse;
import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.*;
import com.fundallassessment.app.enums.TransactionType;
import com.fundallassessment.app.execptions.InsufficientFundsException;
import com.fundallassessment.app.repositories.WalletRepository;
import com.fundallassessment.app.service.WalletService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class WalletServiceImplementation implements WalletService {
    private final WalletRepository walletRepository;
    private final PasswordEncoder encoder;
    private final Dotenv dotenv;
    private final ModelMapper mapper;

    @Override
     public void createWallet(@NonNull User user) {
        log.info("Creating wallet for {}",user.toString() );
        Wallet wallet = Wallet.builder()
                .accountBalance( BigDecimal.valueOf(0.00))
                .accountNumber(user.getPhoneNumber().trim().substring(1))
                .income(BigDecimal.valueOf(0.00))
                .spent(BigDecimal.valueOf(0.00))
                .user(user)
                .pin(encoder.encode(dotenv.get("INITIAL_WALLET_PIN")))
                .build();
        walletRepository.save(wallet);
        log.info("wallet successfully created for {}", user);

    }

    @Override
    @Transactional
    public UserInfoResponse returnUserWalletInfo(@NonNull User user) {
        log.info("getting the user wallet info of {}", user.toString());

        Wallet wallet = walletRepository.findWalletByUser(user).get();


        UserInfoResponse userInfoResponse = new UserInfoResponse();
        List<CardResponse> cardResponseList = new ArrayList<>();
        log.info("Getting all the available card");
        for(UserCard card : wallet.getUserCards()){
            CardResponse cardResponse = mapper.map(card, CardResponse.class);
            cardResponseList.add(cardResponse);
        }

        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for(Transaction transaction : wallet.getTransactions()){
            TransactionResponse transactionResponse = mapper.map(transaction, TransactionResponse.class);
            transactionResponses.add(transactionResponse);
        }
        log.info("returning the user wallet information");

        userInfoResponse.setWalletResponseDetail(
                wallet.getAccountNumber(),
                wallet.getAccountBalance(),
                wallet.getIncome(),
                wallet.getSpent(),
                cardResponseList,
                transactionResponses);

        log.info("the user wallet info is {}", userInfoResponse);
        return userInfoResponse;
    }

    @Override
    @Transactional
    public UserInfoResponse setCashFlow(@NonNull User user, @NonNull BigDecimal amount, @NonNull TransactionType type) {

        log.info("Initiating a {} transaction for " + user, type.name());
        UserInfoResponse userInfoResponse = returnUserWalletInfo(user);
        Wallet wallet = walletRepository.findWalletByUser(user).get();


        if(amount.compareTo(new BigDecimal(0))<=0 || (amount.compareTo(wallet.getAccountBalance()) >0 && type == TransactionType.DEBIT) ){
            log.error("Insufficient funds for this transaction ");
            userInfoResponse.setIsSuccess(false);
            return userInfoResponse;


        }


        if(type == TransactionType.CREDIT){
            System.out.println("Credit ");
            wallet.setAccountBalance(wallet.getAccountBalance().add(amount));
            wallet.setIncome(wallet.getIncome().add(amount));
        }
        else  {
            System.out.println("Inside Debit ");
            wallet.setAccountBalance(wallet.getAccountBalance().subtract(amount));
            wallet.setIncome(wallet.getSpent().add(amount));
        }
        userInfoResponse.setIsSuccess(true);
        log.info("the new wallet history is {}", wallet);
        return userInfoResponse;

    }

    @Override
    @Transactional
    public UserInfoResponse addACardToUserWallet(@NonNull User user, @NonNull UserCard userCard) {
        Wallet wallet = walletRepository.findWalletByUser(user).get();
        setCashFlow(user,userCard.getCardId().getCardCost(),TransactionType.DEBIT );
        userCard.setUserCardNumber(encoder.encode(userCard.getUserCardNumber().concat(wallet.getAccountNumber())));
        wallet.setUserCards(Set.of(userCard));
        return returnUserWalletInfo(user);



    }
}
