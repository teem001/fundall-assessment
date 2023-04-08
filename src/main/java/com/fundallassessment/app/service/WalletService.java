package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.UserCard;
import com.fundallassessment.app.entities.Wallet;
import com.fundallassessment.app.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Optional;

public interface WalletService<T> {

     void createWallet(User user);
     UserInfoResponse returnUserWalletInfo(User user);
     UserInfoResponse setCashFlow(User user, BigDecimal amount, TransactionType type );
     UserInfoResponse addACardToUserWallet(User user, UserCard userCard);


}
