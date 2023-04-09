package com.fundallassessment.app.repositories;

import com.fundallassessment.app.entities.CategoryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryCardRepository extends JpaRepository<CategoryCard, Long> {

    Optional<CategoryCard> findCategoryCardByCategoryTitle(String title);
}
