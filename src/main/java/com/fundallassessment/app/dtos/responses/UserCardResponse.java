package com.fundallassessment.app.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Builder
@Data

public class UserCardResponse {

    private String cardName;
    private BigDecimal amount;
    private UserInfoResponse userInfoResponse;
    private Boolean isSuccess;
    private String message;

}
