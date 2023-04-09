package com.fundallassessment.app.entities;

import com.fundallassessment.app.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;

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
    @Enumerated(
            value = EnumType.STRING
    )
    private CategoryType categoryType;
    private BigDecimal threshold;
    @ManyToOne
    private User user;



}
