package com.fundallassessment.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Data
@Builder
@AllArgsConstructor


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
    @Column(
            unique = true
    )
    private String cardNumber;
    @Column(
            unique = true
    )
    private String cardName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal cardCost;
    @ManyToOne
    private User createdBy;
    @OneToOne
    private UserCard userCard;


    public Card() {

    }

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    public void setUpdatedAt(){
      this.updatedAt = LocalDateTime.now();
    }
}
