package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.Wallet;

import java.util.Optional;

public interface WalletService {

     void createWallet(User user);
     UserInfoResponse returnUserWalletInfo(User user);

}
