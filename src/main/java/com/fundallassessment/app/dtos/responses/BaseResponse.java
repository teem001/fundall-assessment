package com.fundallassessment.app.dtos.responses;

 abstract class BaseResponse {
     private Boolean isSuccess;
     private String message;

     public BaseResponse(Boolean isSuccess, String message) {
         this.isSuccess = isSuccess;
         this.message = message;
     }
 }
