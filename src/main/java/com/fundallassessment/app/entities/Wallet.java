package com.fundallassessment.app.entities;

import com.fundallassessment.app.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Wallet extends Base {

    @Id
    @SequenceGenerator(
            name = "wallet_id_generator",
            sequenceName = "wallet_id_generator"
    )
    private Long walletId;
    private String accountNumber;
    private BigDecimal accountBalance;
    private BigDecimal income;
    private BigDecimal spent;
    private String pin;
    @OneToOne
    @JoinColumn(name = "user_user_id")
    private User user;
    @OneToMany(mappedBy = "wallet")
    private List<Card> bankDetails;

    @OneToMany (mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<TransactionStatus> transactions;
}


