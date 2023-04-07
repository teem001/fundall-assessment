package com.fundallassessment.app.entities;

import com.fundallassessment.app.enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends Base {
    @Id
    @SequenceGenerator(
            name = "transaction_id_sequence",
            sequenceName = "transaction_id_sequence"
    )
    private Long transactionId;
    private String transactionReference;
    private String transactionDescription;
    private TransactionStatus transactionStatus;
    private BigDecimal amount;

}
