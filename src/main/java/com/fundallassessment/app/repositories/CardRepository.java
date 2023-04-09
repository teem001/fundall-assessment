package com.fundallassessment.app.repositories;

import com.fundallassessment.app.entities.Card;
import com.fundallassessment.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(
            value ="SELECT * FROM card",
            nativeQuery = true
    )
    Optional<List<Card>> getAllCard();

    Optional<List<Card>> getAllByCreatedBy(User user);

    @Query(value = "SELECT * FROM card c WHERE c.card_name = ?1 or c.card_number = ?1 ", nativeQuery = true)
   Optional <Card> getCardByCardNumberOOrCardName(String searchKey);
    Optional<Card> getCardByCardNumber(String cardNumber);
}
