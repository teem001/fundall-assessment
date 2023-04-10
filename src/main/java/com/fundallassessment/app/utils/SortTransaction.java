package com.fundallassessment.app.utils;

import com.fundallassessment.app.entities.CategoryCard;

import java.util.Comparator;

public class SortTransaction implements Comparator<CategoryCard> {

    @Override
    public int compare(CategoryCard o1, CategoryCard o2) {
        return o1.getAmount().compareTo(o2.getAmount());

    }
}
