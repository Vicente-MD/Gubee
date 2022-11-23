package com.aurum.aurumapp.checkingaccount.model;

import com.aurum.aurumapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingAccountDTO {
    private CheckingAccount checkingAccount;
    private User user;
}
