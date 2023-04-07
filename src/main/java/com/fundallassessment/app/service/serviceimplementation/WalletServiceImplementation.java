package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.Wallet;
import com.fundallassessment.app.repositories.WalletRepository;
import com.fundallassessment.app.service.WalletService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@AllArgsConstructor
public class WalletServiceImplementation implements WalletService {
    private final WalletRepository walletRepository;
    private PasswordEncoder encoder;
    private Dotenv dotenv;
    @Override
    public void createWallet(User user) {
        Wallet wallet = Wallet.builder()
                .accountBalance(new BigDecimal(0.00))
                .accountNumber(user.getPhoneNumber().trim().substring(1))
                .pin(encoder.encode(dotenv.get("INITIAL_WALLET_PIN")))
                .build();

    }
}
