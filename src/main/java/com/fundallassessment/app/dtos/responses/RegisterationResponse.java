package com.fundallassessment.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterationResponse {
    private String message;
    private Boolean isSuccess;
}
