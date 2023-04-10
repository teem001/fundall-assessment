package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.TransactionAnalysisRequest;
import com.fundallassessment.app.dtos.responses.*;
import com.fundallassessment.app.entities.CategoryCard;
import com.fundallassessment.app.entities.MerchantTracker;
import com.fundallassessment.app.entities.Transaction;
import com.fundallassessment.app.enums.CategoryType;
import com.fundallassessment.app.enums.TransactionType;
import com.fundallassessment.app.repositories.CategoryCardRepository;
import com.fundallassessment.app.repositories.MerchantTrackerRepository;
import com.fundallassessment.app.repositories.TransactionRepository;
import com.fundallassessment.app.service.TransactionAnalysisService;
import com.fundallassessment.app.utils.SortTransaction;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class TransactionAnalysisServiceImplementation  implements TransactionAnalysisService {
    private final TransactionRepository transactionRepository;
    private final CategoryCardRepository cardRepository;
    private final MerchantTrackerRepository trackerRepository;

    @Override
    public ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(@NonNull TransactionAnalysisRequest period) {
        Integer year = period.getYear();
        Integer month = period.getMonth();
        Integer day = period.getDay();

        List<MerchantTracker> listOfTopThreeMerchant ;


        List<Transaction> list;
        List<CategoryCard> categoryCards;

        if(day==null  && month == null ){
            list = transactionRepository.getAllTransactionByYear(year).get();
            categoryCards = cardRepository.getAllTransactionOnCategoryByYear(year).get();
            listOfTopThreeMerchant = trackerRepository.getAllTransactionByYear(year).get();
        }
        else if(day ==null){
            list = transactionRepository.getAllTransactionByMonth(year, month).get();
            categoryCards = cardRepository.getAllTransactionOnCategoryByMonth(year,month).get();
            listOfTopThreeMerchant = trackerRepository.getAllTransactionByMonth(year,month).get();

        }
        else {
            list = transactionRepository.getAllTransactionByDay(year,month,day).get();

            categoryCards= cardRepository.getAllTransactionOnCategoryByDay(year,month,day).get();
            listOfTopThreeMerchant= trackerRepository.getAllTransactionByDay(year,month,day).get();
        }



        return ResponseEntity.ok(getTransactionAnalysis(list,categoryCards,listOfTopThreeMerchant));
    }

    @Override
    public ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(TransactionAnalysisRequest startPeriod, TransactionAnalysisRequest endPeriod) {

        LocalDateTime startDate = LocalDateTime.of(startPeriod.getYear(),startPeriod.getMonth(),startPeriod.getDay(),0,0,0);
        LocalDateTime endDate = LocalDateTime.of(endPeriod.getYear(),endPeriod.getMonth(),endPeriod.getDay(),0,0,0);
        List<MerchantTracker> listOfTopThreeMerchant ;
        List<Transaction> list;
        List<CategoryCard> categoryCards;

        list = transactionRepository.getAllTransactionFromCustomPeriod(startDate,endDate).get();
        categoryCards = cardRepository.getAllTransactionOnCategoryFromCustomPeriod(startDate,endDate).get();
        listOfTopThreeMerchant = trackerRepository.getAllTransactionFromCustomPeriod(startDate,endDate).get();;


        return ResponseEntity.ok(getTransactionAnalysis(list,categoryCards,listOfTopThreeMerchant));
    }


    private TransactionAnalysisResponse getTransactionAnalysis( List<Transaction> list,
    List<CategoryCard> categoryCards, List<MerchantTracker> listOfTopThreeMerchant ){
        BigDecimal spending ;
        BigDecimal inflows;
        BigDecimal expenses;
        HashMap<CategoryResponse, Double> incomeTracker = new HashMap<>();
        BigDecimal averageIncome ;


        spending = list.stream().filter(p-> p.getType()==TransactionType.DEBIT).map(Transaction::getAmount).reduce(BigDecimal::add).get();

        inflows = categoryCards.stream().filter(p-> p.getCategoryType() == CategoryType.INCOME).map(CategoryCard::getAmount).reduce(BigDecimal::add).get();

        expenses = categoryCards.stream().filter(p-> p.getCategoryType() == CategoryType.EXPENSE).map(CategoryCard::getAmount).reduce(BigDecimal::add).get();
        categoryCards.sort(new SortTransaction());

        averageIncome = categoryCards.size()>0 ? inflows.divide(new BigDecimal(categoryCards.size())): inflows;

        for(CategoryCard card: categoryCards){
            if(card.getCategoryType()==CategoryType.INCOME){
                Double incomeFraction = card.getAmount().doubleValue() / inflows.doubleValue();

                CategoryCardIncomeResponse categoryCardIncomeResponse=  CategoryCardIncomeResponse.builder()
                        .message("available")
                        .isSuccess(true)
                        .amount(card.getAmount())
                        .categoryTitle(card.getCategoryTitle())
                        .categoryType("INCOME")
                        .build();
                incomeTracker.put(categoryCardIncomeResponse, incomeFraction);

            }

        }
        List<Merchant> topMerchant = List.of(
                new Merchant(listOfTopThreeMerchant.get(0).getName(),
                        listOfTopThreeMerchant.get(0).getNumberOfTimes(),
                        listOfTopThreeMerchant.get(0).getTotalAmount()),
                new Merchant(listOfTopThreeMerchant.get(1).getName(),
                        listOfTopThreeMerchant.get(1).getNumberOfTimes(),
                        listOfTopThreeMerchant.get(1).getTotalAmount()),
                new Merchant(listOfTopThreeMerchant.get(2).getName(),
                        listOfTopThreeMerchant.get(2).getNumberOfTimes(),
                        listOfTopThreeMerchant.get(2).getTotalAmount()));


        return  TransactionAnalysisResponse.builder()
                .expenses(expenses)
                .incomeTracker(incomeTracker)
                .inflows(inflows)
                .listOfTopThreeMerchant(topMerchant)
                .spending(spending)
                .topCategories(List.of(categoryCards.get(0),
                        categoryCards.get(1),
                        categoryCards.get(2)))
                .averageIncome(averageIncome)
                .totalIncome(inflows)

                .build();

    }


/*  TODO   private BigDecimal spending;
     private BigDecimal inflows;
      private BigDecimal expenses;
      private List<Merchant> listOfTopThreeMerchant;
      private List<CategoryCard> topCategories;
      private HashMap<CategoryCard, Double> incomeTracker; */
}
