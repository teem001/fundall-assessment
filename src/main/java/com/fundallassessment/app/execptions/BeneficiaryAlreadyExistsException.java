package com.fundallassessment.app.execptions;

public class BeneficiaryAlreadyExistsException extends RuntimeException {
    public BeneficiaryAlreadyExistsException (String message)  {
        super(message);
    };
}
