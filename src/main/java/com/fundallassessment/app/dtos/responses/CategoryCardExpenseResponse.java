package com.fundallassessment.app.dtos.responses;

import lombok.*;

import java.math.BigDecimal;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
public class CategoryCardExpenseResponse extends CategoryResponse {
    private BigDecimal amount;
    private String categoryType;
    private String categoryTitle;
    private BigDecimal threshold;
    private boolean isSuccess;
    private String message;

}
