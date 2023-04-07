package com.fundallassessment.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Data
@Builder


public class Card {
    @Id
    @SequenceGenerator(
            name = "card_id_sequence",
            sequenceName = "card_id_sequence"
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_id_sequence"
    )
    private Long cardId;
    private String cardNumber;
    private String cardName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal cardCost;

    public Card() {

    }
}
