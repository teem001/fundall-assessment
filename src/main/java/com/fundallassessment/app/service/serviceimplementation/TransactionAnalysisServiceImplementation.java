package com.fundallassessment.app.service.serviceimplementation;

import com.fundallassessment.app.dtos.requests.TransactionAnalysisRequest;
import com.fundallassessment.app.dtos.responses.TransactionAnalysisResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class TransactionAnalysisServiceImplementation  implements TransactionAnalysisService {
    private final TransactionRepository transactionRepository;
    private final CategoryCardRepository cardRepository;
    private final MerchantTrackerRepository trackerRepository;

    @Override
    public ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(TransactionAnalysisRequest period) {
        Integer year = period.getYear();
        Integer month = period.getMonth();
        Integer day = period.getDay();
        BigDecimal spending ;
        BigDecimal inflows;
        BigDecimal expenses;
        List<MerchantTracker> listOfTopThreeMerchant ;
        List<CategoryCard> topCategories = new ArrayList<>();
        HashMap<CategoryCard, Double> incomeTracker = new HashMap<>();

        List<Transaction> list;
        List<CategoryCard> categoryCards;
        TransactionAnalysisResponse response = new TransactionAnalysisResponse();

        if(day== null  && month == null ){
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

       spending = list.stream().filter(p-> p.getType()==TransactionType.DEBIT).map(Transaction::getAmount).reduce(BigDecimal::add).get();

        inflows = categoryCards.stream().filter(p-> p.getCategoryType() == CategoryType.INCOME).map(CategoryCard::getAmount).reduce(BigDecimal::add).get();

        expenses = categoryCards.stream().filter(p-> p.getCategoryType() == CategoryType.EXPENSE).map(CategoryCard::getAmount).reduce(BigDecimal::add).get();
        list.sort(new SortTransaction());



        return null;
    }

    @Override
    public ResponseEntity<TransactionAnalysisResponse> getAllTransactionForPeriod(TransactionAnalysisRequest startPeriod, TransactionAnalysisRequest endPeriod) {
        return null;
    }


/*  TODO   private BigDecimal spending;
     private BigDecimal inflows;
      private BigDecimal expenses;
      private List<Merchant> listOfTopThreeMerchant;
      private List<CategoryCard> topCategories;
      private HashMap<CategoryCard, Double> incomeTracker; */
}
