package com.fundallassessment.app.controllers;

import com.fundallassessment.app.dtos.responses.UserInfoResponse;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.service.WalletService;
import com.fundallassessment.app.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/assessment/wallet")
public class WalletController {
    private final WalletService walletService;
    private final UserUtils utils;
    @GetMapping
  ResponseEntity<UserInfoResponse> returnUserWalletInfo(User user){
        user = utils.getLoggedInUser();

        return user != null
                ? ResponseEntity
                .ok(
                        walletService
                                .returnUserWalletInfo(user)
                ):
                ResponseEntity
                        .notFound()
                        .build();

    }
}
