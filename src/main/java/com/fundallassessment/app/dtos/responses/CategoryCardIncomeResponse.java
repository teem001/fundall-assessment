package com.fundallassessment.app.dtos.responses;

import lombok.*;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NonNull
public class CategoryCardIncomeResponse extends CategoryResponse {
    private BigDecimal amount;
    private String categoryTitle;
    private String categoryType;
    private boolean isSuccess;
    private String message;


}
