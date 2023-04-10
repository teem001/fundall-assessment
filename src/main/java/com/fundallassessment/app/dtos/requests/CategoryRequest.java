package com.fundallassessment.app.dtos.requests;

import com.fundallassessment.app.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    private String categoryTitle;
    private String description;
    private CategoryType categoryType;
    private BigDecimal threshold;

}
