package com.fundallassessment.app.entities;

import com.fundallassessment.app.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryCard extends Base {


    private BigDecimal amount;
    private String categoryTitle;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean onTrack;
    @Enumerated(
            value = EnumType.STRING
    )
    private CategoryType categoryType;
    private BigDecimal threshold;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Transaction> transaction;


    public void setOnTrack(){
        this.onTrack = this.categoryType == CategoryType.EXPENSE? this.amount.compareTo( this.threshold)<=0:
                this.categoryType == CategoryType.INCOME && this.amount.compareTo(this.threshold) >= 0;
    }

}
