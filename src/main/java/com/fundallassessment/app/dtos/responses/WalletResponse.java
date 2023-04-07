package com.fundallassessment.app.dtos.responses;




import java.math.BigDecimal;
import java.util.List;

class WalletResponse {
    protected String accountNumber;
    protected BigDecimal accountBalance;
    protected BigDecimal income;
    protected BigDecimal spent;
    protected List<CardResponse> cardResponses;
    protected List<TransactionResponse> transactions;

    public WalletResponse(String accountNumber, BigDecimal accountBalance, BigDecimal income, BigDecimal spent, List<CardResponse> cardResponses, List<TransactionResponse> transactions) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.income = income;
        this.spent = spent;
        this.cardResponses = cardResponses;
        this.transactions = transactions;
    }

    public WalletResponse() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }


    public List<CardResponse> getCardResponses() {
        return cardResponses;
    }

    public void setCardResponses(List<CardResponse> cardResponses) {
        this.cardResponses = cardResponses;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }

}
