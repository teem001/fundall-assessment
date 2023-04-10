package com.fundallassessment.app.dtos.responses;



import com.fundallassessment.app.entities.CategoryCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAnalysisResponse {
    private BigDecimal spending;
    private BigDecimal inflows;
    private BigDecimal expenses;
    private List<Merchant> listOfTopThreeMerchant;
    private List<CategoryCard> topCategories;
    private HashMap<CategoryResponse, Double> incomeTracker;
    private BigDecimal totalIncome;
    private BigDecimal averageIncome ;

}
