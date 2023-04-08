package com.fundallassessment.app.execptions;


public class InvalidTransactionException extends RuntimeException {

    private String message;

    public InvalidTransactionException() {
        super();
        message = "Invalid transaction";
    }

    public InvalidTransactionException(String message) {
        super(message);
        this.message = message;
    }
}
