package com.fundallassessment.app.utils;

import com.fundallassessment.app.entities.User;
import lombok.AllArgsConstructor;

import java.security.Principal;
@AllArgsConstructor
public class UserUtill {
    private final Principal principal;
    public String getLoggedInUser(){
        return principal.getName();
    }


}
