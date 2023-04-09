package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.CategoryRequest;
import com.fundallassessment.app.dtos.responses.CategoryCardExpenseResponse;
import com.fundallassessment.app.dtos.responses.CategoryCardIncomeResponse;
import com.fundallassessment.app.dtos.responses.CategoryResponse;
import com.fundallassessment.app.entities.CategoryCard;
import com.fundallassessment.app.entities.User;
import com.fundallassessment.app.enums.CategoryType;
import com.fundallassessment.app.repositories.CategoryCardRepository;
import com.fundallassessment.app.repositories.UserRepository;
import com.fundallassessment.app.service.CategoryCardService;
import com.fundallassessment.app.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CategoryCardServiceImplementation implements CategoryCardService {
    private final CategoryCardRepository categoryCardRepository;
    private final UserUtils utils;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public CategoryResponse createACategory(CategoryRequest request) {
        User user = utils.getLoggedInUser();
      Optional<CategoryCard> cardOptional= categoryCardRepository.findCategoryCardByCategoryTitle(request.getCategoryTitle());
      if (cardOptional.isPresent()){
          return request.getCategoryType() == CategoryType.EXPENSE ?
                  CategoryCardExpenseResponse.builder()
                          .message("Expense category "+ request.getCategoryTitle() + " already exits")
                          .isSuccess(false)
                          .build() :
                  CategoryCardIncomeResponse.builder()
                          .message("Income category "+ request.getCategoryTitle() + " already exist")
                          .isSuccess(true)
                          .build();
      }
        CategoryCard card = CategoryCard.builder().build();
        mapper.map(request,card);


        categoryCardRepository.saveAndFlush(card);
        card.setUser(user);
        return card.getCategoryType() == CategoryType.EXPENSE ?
                CategoryCardExpenseResponse.builder()
                        .message("Expense category "+ card.getCategoryTitle() + " created")
                        .isSuccess(true)
                        .categoryType(card.getCategoryType().name())
                        .amount(new BigDecimal("0.00"))
                        .threshold(card.getThreshold())
                        .categoryTitle(card.getCategoryTitle())
                        .build() :
                CategoryCardIncomeResponse.builder()
                        .message("Income category "+ card.getCategoryTitle() + " created")
                        .isSuccess(true)
                        .categoryType(card.getCategoryType().name())
                        .amount(new BigDecimal("0.00"))
                        .categoryTitle(card.getCategoryTitle()).build();
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request) {
        return null;
    }

    @Override
    public CategoryResponse getAllCategory() {

        return null;
    }

    @Override
    public void deleteCategory(String category) {

    }
}
