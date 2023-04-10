package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.CategoryRequest;
import com.fundallassessment.app.dtos.responses.CategoryResponse;
import com.fundallassessment.app.enums.CategoryType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryCardService {

    CategoryResponse createACategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategory();
    List<CategoryResponse> getAllCategoryByCategoryType(CategoryType type);

    void deleteCategory(String category);


}
