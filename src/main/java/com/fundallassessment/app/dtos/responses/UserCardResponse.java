package com.fundallassessment.app.dtos.responses;

import java.math.BigDecimal;

public class UserCardResponse extends BaseResponse{

    private String cardName;
    private BigDecimal amount;
    private UserInfoResponse userInfoResponse;
    public UserCardResponse(Boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}
