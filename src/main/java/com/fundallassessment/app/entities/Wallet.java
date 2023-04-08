package com.fundallassessment.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Wallet extends Base {


    private String accountNumber;
    private BigDecimal accountBalance;
    private BigDecimal income;
    private BigDecimal spent;
    private String pin;
    @OneToOne

    private User user;
    @OneToMany
    private Set<UserCard> userCards;

    @OneToMany
    private List<Transaction> transactions;
}


