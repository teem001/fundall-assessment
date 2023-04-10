package com.fundallassessment.app.utils;

import com.fundallassessment.app.entities.Transaction;

import java.util.Comparator;

public class SortTransaction implements Comparator<Transaction> {

    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.getAmount().compareTo(o2.getAmount());

    }
}
