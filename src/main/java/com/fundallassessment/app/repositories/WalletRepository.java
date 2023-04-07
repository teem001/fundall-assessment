package com.fundallassessment.app.repositories;

import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    Optional<Wallet> findWalletByUser(User user);
}
