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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
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
    public CategoryResponse createACategory(@NotNull CategoryRequest request) {
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
        card.setOnTrack();


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
    @Transactional
    public CategoryResponse updateCategory(@NotNull CategoryRequest request) {
       Optional <CategoryCard> card = categoryCardRepository.findCategoryCardByCategoryTitle(request.getCategoryTitle());


       return card.map(p->{ mapper.map(request, p);
           CategoryCardIncomeResponse  response = CategoryCardIncomeResponse.builder()
                   .isSuccess(true)
                   .categoryType(p.getCategoryTitle())
                   .amount(p.getAmount())
                   .categoryType(p.getCategoryType().name())
                   .build();
           return response;} ).orElseGet(
               ()-> CategoryCardIncomeResponse.builder()
                       .message("unsuccessful")
                       .isSuccess(false)
                       .build()
       );





    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        User user = utils.getLoggedInUser();
        Optional<List<CategoryCard>> card= categoryCardRepository.findCategoryCardByUser(user);

        return getCategoryResponses(card);


    }

    @Override
    public void deleteCategory(String category) {


    }

    @Override
    public List <CategoryResponse> getAllCategoryByCategoryType(CategoryType type) {
        User user = utils.getLoggedInUser();
        Optional<List<CategoryCard>> card= categoryCardRepository.findCategoryCardByUserAndCategoryType(user,type);

        return getCategoryResponses(card);
    }

    private List<CategoryResponse> getCategoryResponses(Optional<List<CategoryCard>> card) {
        return card.map(p-> p.stream().map(a->
                a.getCategoryType()== CategoryType.EXPENSE ? CategoryCardExpenseResponse.builder()
                        .isSuccess(true)
                        .message("is Available")
                        .amount(a.getAmount())
                        .threshold(a.getThreshold())
                        .categoryTitle(a.getCategoryTitle())
                        .build(): CategoryCardIncomeResponse.builder()
                        .isSuccess(true)
                        .message("is Available")
                        .amount(a.getAmount())
                        .categoryTitle(a.getCategoryTitle())
                        .build()
        ).toList()).orElseGet(()->List.of(
                CategoryCardIncomeResponse.builder()
                        .message("unavailable for Income category")
                        .isSuccess(false)
                        .build(),
                CategoryCardExpenseResponse.builder()
                        .message("unavailable for expense category")
                        .isSuccess(false)
                        .build()
        ));
    }


}
