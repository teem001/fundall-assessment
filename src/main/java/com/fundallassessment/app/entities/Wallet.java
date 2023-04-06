package com.fundallassessment.app.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Wallet {
    private Long walletId;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String pin;
//    @OneToOne
//    @JoinColumn(name = "user_user_id")
    private User user;
//    private WalletStatus status;
//    @OneToMany(mappedBy = "wallet")
//    private List<BankDetails> bankDetails;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate lastTransactionDate;
//    @OneToMany (mappedBy = "wallet", cascade = CascadeType.ALL)
//    private List<Transaction> transactions;
}


