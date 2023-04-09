package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.Transaction;
import com.fundallassessment.app.entities.User;


public interface WalletService {

     void createWallet(User user);
     UserInfoResponse returnUserWalletInfo(User user);

     UserInfoResponse setCashFlow(User user, Transaction transaction);


}
