package com.fundallassessment.app.repositories;

import com.fundallassessment.app.entities.CategoryCard;
import com.fundallassessment.app.entities.Transaction;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryCardRepository extends JpaRepository<CategoryCard, Long> {

    Optional<CategoryCard> findCategoryCardByCategoryTitle(String title);
    Optional<List<CategoryCard>> findCategoryCardByUser(User user);

    Optional<List<CategoryCard>> findCategoryCardByUserAndCategoryType(User user, CategoryType type);

    @Query(value = "SELECT * FROM category_card t WHERE EXTRACT(YEAR FROM t.createdAt)=?1 ",
            nativeQuery = true)
    Optional<List<CategoryCard>> getAllTransactionOnCategoryByYear(Integer year );
    @Query(value = "SELECT * FROM category_card t WHERE" +
            " EXTRACT(YEAR FROM t.createdAt)=?1 " +
            "AND EXTRACT(MONTH FROM t.createdAt)=?2",
            nativeQuery = true)

    Optional<List<CategoryCard>> getAllTransactionOnCategoryByMonth(Integer year ,Integer month );
    @Query(value = "SELECT * FROM category_card t" +
            " WHERE EXTRACT(YEAR FROM t.createdAt)=?1 " +
            "AND EXTRACT(MONTH FROM t.createdAt)=?2 " +
            "AND  EXTRACT(DAY FROM t.createdAt)=?3",
            nativeQuery = true)
    Optional<List<CategoryCard>> getAllTransactionOnCategoryByDay(Integer year ,Integer month,Integer day );

    @Query(value = "SELECT * FROM category_card t" +
            " WHERE t.createdAt BETWEEN ?1 AND ?2",
            nativeQuery = true)
    Optional<List<CategoryCard>> getAllTransactionOnCategoryFromCustomPeriod(LocalDateTime startDate, LocalDateTime endDate);


}
