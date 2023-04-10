package com.fundallassessment.app.repositories;

import com.fundallassessment.app.entities.MerchantTracker;
import com.fundallassessment.app.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantTrackerRepository extends JpaRepository<MerchantTracker, Long> {

    Optional<MerchantTracker> getMerchantTrackerByName(String name);

    @Query(value = "SELECT * FROM (SELECT * FROM merchant_tracker t WHERE EXTRACT(YEAR FROM t.createdAt)=?1)" +
            "AS t ORDER BY t.total_amount DESC LIMIT 3",
            nativeQuery = true)
    Optional<List<MerchantTracker>> getAllTransactionByYear(Integer year );
    @Query(value = "SELECT * FROM (SELECT * FROM merchant_tracker t WHERE" +
            " EXTRACT(YEAR FROM t.createdAt)=?1 " +
            "AND EXTRACT(MONTH FROM t.createdAt)=?2) AS t ORDER BY t.total_amount DESC LIMIT 3",
            nativeQuery = true)

    Optional<List<MerchantTracker>> getAllTransactionByMonth(Integer year ,Integer month );
    @Query(value = "SELECT * FROM (SELECT * FROM merchant_tracker t" +
            " WHERE EXTRACT(YEAR FROM t.createdAt)=?1 " +
            "AND EXTRACT(MONTH FROM t.createdAt)=?2 " +
            "AND  EXTRACT(DAY FROM t.createdAt)=?3) AS t ORDER BY t.total_amount DESC LIMIT 3",
            nativeQuery = true)
    Optional<List<MerchantTracker>> getAllTransactionByDay(Integer year ,Integer month,Integer day );

    @Query(value = "SELECT * FROM ( SELECT * FROM merchant_tracker t" +
            " WHERE t.createdAt BETWEEN ?1 AND ?2) AS t ORDER BY t.total_amount DESC LIMIT 3",
            nativeQuery = true)
    Optional<List<MerchantTracker>> getAllTransactionFromCustomPeriod(LocalDateTime startDate, LocalDateTime endDate);




}
