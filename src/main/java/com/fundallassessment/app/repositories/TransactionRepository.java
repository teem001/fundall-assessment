package com.fundallassessment.app.repositories;


import com.fundallassessment.app.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction t WHERE EXTRACT(YEAR FROM t.createdAt)=?1 ",
    nativeQuery = true)
    Optional<List<Transaction>> getAllTransactionByYear(Integer year );
    @Query(value = "SELECT * FROM transaction t WHERE" +
            " EXTRACT(YEAR FROM t.createdAt)=?1 " +
            "AND EXTRACT(MONTH FROM t.createdAt)=?2",
            nativeQuery = true)

    Optional<List<Transaction>> getAllTransactionByMonth(Integer year ,Integer month );
    @Query(value = "SELECT * FROM transaction t" +
            " WHERE EXTRACT(YEAR FROM t.createdAt)=?1 " +
            "AND EXTRACT(MONTH FROM t.createdAt)=?2 " +
            "AND  EXTRACT(DAY FROM t.createdAt)=?3",
            nativeQuery = true)
    Optional<List<Transaction>> getAllTransactionByDay(Integer year ,Integer month,Integer day );

    @Query(value = "SELECT * FROM transaction t" +
            " WHERE t.createdAt BETWEEN ?1 AND ?2",
            nativeQuery = true)
    Optional<List<Transaction>> getAllTransactionFromCustomPeriod(LocalDateTime startDate, LocalDateTime endDate);





}