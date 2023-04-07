package com.fundallassessment.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponse {
    private String token;
    private String imgPath;
    private String message;
    private WalletResponse walletResponse;
    private Boolean isSuccess;


   public void setWalletResponseDetail(String accountNumber, BigDecimal accountBalance, BigDecimal income, BigDecimal spent, List<CardResponse> cardResponses, List<TransactionResponse> transactions){
       this.walletResponse.cardResponses =cardResponses;
      this.walletResponse.accountBalance = accountBalance;
      this.walletResponse.transactions = transactions;
      this.walletResponse.income = income;
      this.walletResponse.spent = spent;
      this.walletResponse.accountNumber = accountNumber;

    }


}
