package com.fundallassessment.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserCard extends Base {

    private Long userCardId;
    @OneToOne(mappedBy = "userCard")
    private Card card;
    private String userCardNumber;
    private BigDecimal amountOnTheCard;
    @ManyToOne
    private Wallet wallet;

}

