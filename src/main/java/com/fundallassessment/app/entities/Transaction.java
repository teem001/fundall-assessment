package com.fundallassessment.app.entities;

import com.fundallassessment.app.enums.TransactionStatus;
import com.fundallassessment.app.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name="user_transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends Base {

    private String transactionReference;
    private String transactionDescription;
    private String name;
    private String referenceNumber;
    @Enumerated(
            value = EnumType.STRING
    )
    private TransactionStatus transactionStatus;
    @Enumerated(
                    value = EnumType.STRING
            )
    private TransactionType type;
    private BigDecimal amount;
    @ManyToOne
    private Wallet wallet;

}
