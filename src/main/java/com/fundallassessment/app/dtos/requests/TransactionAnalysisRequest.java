package com.fundallassessment.app.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAnalysisRequest {
    private Integer year;
    private Integer month;
    private Integer day;


}
