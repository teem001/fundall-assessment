package com.fundallassessment.app.service;

import com.fundallassessment.app.dtos.requests.CategoryRequest;
import com.fundallassessment.app.dtos.responses.CategoryResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryCardService {

    CategoryResponse createACategory(CategoryRequest request);
    CategoryResponse updateCategory(CategoryRequest request);
    CategoryResponse getAllCategory();
    void deleteCategory(String category);


}
